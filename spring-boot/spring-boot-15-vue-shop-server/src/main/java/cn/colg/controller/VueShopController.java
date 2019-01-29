package cn.colg.controller;

import static cn.colg.util.ResultVoUtil.fail;
import static cn.colg.util.ResultVoUtil.success;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.colg.model.User;
import cn.colg.util.SmsUtil;
import cn.colg.vo.ResultVo;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 外卖后台管理
 *
 * @author colg
 */
@Slf4j
@CrossOrigin
@RestController
public class VueShopController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /** 定义map用于存储验证码 */
    private HashMap<String, String> users = new HashMap<>();

    /**
     * 1、根据经纬度获取位置详情
     *
     * @param geohash 经纬度
     * @return
     * @author colg
     */
    @GetMapping("/position/{geohash}")
    public ResultVo position(@PathVariable String geohash) {
        log.info("/position/{geohash}; geohash: {}", geohash);
        String data = HttpUtil.get("http://cangdu.org:8001/v2/pois/" + geohash);
        return success(JSON.parse(data));
    }

    /**
     * 2、获取食品分类列表
     *
     * @return
     * @author colg
     */
    @GetMapping("/index_category")
    public ResultVo index_category() {
        log.info("/index_category");
        String data = ResourceUtil.readUtf8Str("data/index_category.json");
        return success(JSON.parse(data));
    }

    /**
     * 3、根据经纬度获取商铺列表
     *
     * @param latitude 经度
     * @param longitude 纬度
     * @return
     * @author colg
     */
    @GetMapping("/shops")
    public ResultVo shops(@RequestParam String latitude, @RequestParam String longitude) {
        log.info("/shops; longitude: {}, latitude: {}", longitude, latitude);
        String data = ResourceUtil.readUtf8Str("data/shops.json");
        return success(JSON.parse(data));
    }

    /**
     * 4、根据经纬度和关键字搜索商铺列表
     *
     * @param geohash 经纬度
     * @param keyword 关键字
     * @return
     * @author colg
     */
    @GetMapping("/search_shops")
    public ResultVo search_shops(@RequestParam String geohash, @RequestParam String keyword) {
        log.info("/search_shops; geohash: {}, keyword: {}", geohash, keyword);
        /*
        Dict paramMap = Dict.create()
                            .set("extras[]", "restaurant_activity")
                            .set("type", "search")
                            .set("geohash", geohash)
                            .set("keyword", keyword);
        */
        Dict paramMap = Dict.create()
                            .set("geohash", geohash)
                            .set("keyword", keyword);
        String data = HttpUtil.get("http://cangdu.org:8001/v4/restaurants", paramMap);
        return success(JSON.parse(data));
    }

    /**
     * 5、获取一次性图形验证码
     *
     * @return
     * @author colg
     * @throws IOException
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response, HttpSession session) throws IOException {
        // 使用Hutool CaptchaUtil 生成图形验证码
        ICaptcha iCaptcha = CaptchaUtil.createLineCaptcha(100, 37, 4, 0);
        OutputStream out = response.getOutputStream();
        iCaptcha.write(out);
        IoUtil.close(out);
        String captcha = iCaptcha.getCode().toUpperCase();
        log.info("/captcha; captcha: {}", captcha);
        // 保存到session
        session.setAttribute("captcha", captcha);
        
        /*
        // 使用Google kaptcha 生成图形验证码
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "100");
        properties.setProperty("kaptcha.image.height", "37");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.textproducer.char.string", "23456789abcdefghjklmnpqrstuvwxyz");
        properties.setProperty("kaptcha.textproducer.font.names", "微软雅黑");
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        String createText = defaultKaptcha.createText();
        BufferedImage challenge = defaultKaptcha.createImage(createText);
        ImageIO.write(challenge, "jpg", jpegOutputStream);

        byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        ServletOutputStream out = response.getOutputStream();
        out.write(captchaChallengeAsJpeg);
        IoUtil.close(out);
        // 保存到session
        session.setAttribute("captcha", createText.toUpperCase());
        */
    }

    /**
     * 6、用户名密码登陆
     *
     * @param name 用户名
     * @param pwd 密码
     * @param captcha 验证码
     * @return
     * @author colg
     */
    @PostMapping("/login_pwd")
    public ResultVo login_pwd(@RequestBody String params, HttpSession session, HttpServletRequest request) {
        JSONObject jsonObject = JSON.parseObject(params);
        log.info("/login_pwd; params: {}", params);
        String captcha = jsonObject.getString("captcha").toUpperCase();

        // 可以对用户名/密码格式进行检查, 如果非法, 返回提示信息
        if (!captcha.equals(session.getAttribute("captcha").toString())) {
            return fail("验证码不正确！");
        }
        // 删除保存的验证码
        session.removeAttribute("captcha");
        
        String name = jsonObject.getString("name");
        String pwd = SecureUtil.md5(jsonObject.getString("pwd"));

        // 根据用户名查询用户
        Query query = new Query(Criteria.where("name").is(name));
        User user = mongoTemplate.findOne(query, User.class);
        if (user != null) {
            if (!pwd.equals(user.getPwd())) {
                return fail("用户名或密码不正确！");
            } else {
                // 保存到session
                session.setAttribute("userid", user.get_id());
                return success(user.setPwd(null));
            }
        } else {
            // 添加到mongodb
            user = new User().setName(name).setPwd(pwd);
            mongoTemplate.save(user);
            user = mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), User.class);
            // 保存到session
            session.setAttribute("userid", user.get_id());
            return success(user.setPwd(null));
        }
    }

    /**
     * 7、发送短信验证码
     *
     * @param phone 手机号
     * @return
     * @author colg
     */
    @GetMapping("/sendcode")
    public ResultVo sendcode(@RequestParam String phone, HttpSession session) {
        // 生成验证码，6位随机数
        String code = RandomUtil.randomNumbers(6);
        log.info("/sendcode; phone: {}, code: {}", phone, code);
        // 发送给指定的手机号
        if (SmsUtil.sendCode(phone, code)) {
            // 保存验证码
            users.put("code", code);
            return success(code);
        } else {
            return fail("短信验证码发送失败！");
        }
    }

    /**
     * 8、手机号验证码登陆
     *
     * @param phone 手机号
     * @param code 验证码
     * @return
     * @author colg
     */
    @PostMapping("/login_sms")
    public ResultVo login_sms(@RequestBody String params, HttpSession session) {
        log.info("/login_sms, params: {}", params);
        JSONObject jsonObject = JSON.parseObject(params);
        String code = jsonObject.getString("code");
        
        if (!code.equals(users.get("code"))) {
            return fail("手机号或验证码不正确！");
        }

        // 删除保存的code
        users.remove("code");

        String phone = jsonObject.getString("phone");
        
        User user = mongoTemplate.findOne(new Query(Criteria.where("phone").is(phone)), User.class);
        if (user != null) {
            // 保存用户到session
            session.setAttribute("userid", user.get_id());
            return success(user.setPwd(null));
        } else {
            // 添加到mongodb
            user = new User().setPhone(phone);
            mongoTemplate.save(user);
            user = mongoTemplate.findOne(new Query(Criteria.where("phone").is(phone)), User.class);
            // 保存到session
            session.setAttribute("userid", user.get_id());
            return success(user.setPwd(null));
        }
    }

    /**
     * 9、根据会话获取用户信息
     *
     * @return
     * @author colg
     */
    @GetMapping("/userinfo")
    public ResultVo userinfo(HttpSession session) {
        log.info("/userinfo; sessionId: {}", session.getId());
        Object o = session.getAttribute("userid");
        if (o == null) {
            return fail("请先登录！");
        }
        String userid = o.toString();
        log.info("/userinfo; userid: {}", userid);

        // 查询用户
        User user = mongoTemplate.findOne(new Query(Criteria.where("_id").is(userid)), User.class);
        if (user == null) {
            // 清除浏览器保存的userid的cookie
            session.removeAttribute("userid");
            return fail("请先登录！");
        } else {
            return success(user);
        }
    }

    /**
     * 10、用户登出
     *
     * @param session
     * @return
     * @author colg
     */
    @GetMapping("/logout")
    public ResultVo logout(HttpSession session) {
        log.info("/logout; {}", DateUtil.now());
        session.removeAttribute("userid");
        return success(null);
    }
    
}
