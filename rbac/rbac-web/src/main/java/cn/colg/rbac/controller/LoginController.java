package cn.colg.rbac.controller;

import cn.colg.rbac.core.BaseController;
import cn.colg.rbac.dto.PermissionDto;
import cn.colg.rbac.entity.User;
import cn.colg.rbac.vo.ResultVo;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

import static cn.colg.rbac.util.CheckUtil.checkNotNull;
import static cn.colg.rbac.util.ResultVoUtil.success;

/**
 * 登录
 *
 * @author colg
 */
@Controller
public class LoginController extends BaseController {

    /**
     * 错误页面
     *
     * @return
     */
    @GetMapping("/error")
    public String error() {
        return "error";
    }

    /**
     * 注销
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect: login";
    }

    /**
     * 主页
     *
     * @return
     */
    @GetMapping("/main")
    public String main() {
        return "main";
    }

    /**
     * ajax 执行登录
     *
     * @param loginacct 登录帐号
     * @param password 密码
     * @return
     */
    @PostMapping("/doAjaxLogin")
    @ResponseBody
    public ResultVo doAjaxLogin(User user, HttpSession session) {
        User dbUser = userService.findUser(user);
        checkNotNull(dbUser, "登录帐号或密码不正确，请重新输入");
        // 保存登录用户
        session.setAttribute("loginUser", dbUser);
        // 获取用户权限信息
        List<PermissionDto> pds = permissionService.queryPermissionsByUser(dbUser);
        Map<String, PermissionDto> permissionDtoMap = new HashMap<>(16);
        // 保存uri
        Set<String> uriSet = new HashSet<>();
        for (PermissionDto pd : pds) {
            permissionDtoMap.put(pd.getId(), pd);

            String url = pd.getUrl();
            if (StrUtil.isNotBlank(url)) {
                uriSet.add(session.getServletContext().getContextPath() + url);
            }
        }
        // 保存用户的url
        session.setAttribute("authUriSet", uriSet);

        PermissionDto root = null;
        for (PermissionDto child : pds) {
            String pid = child.getPid();
            if ("0".equals(pid)) {
                root = child;
            } else {
                PermissionDto parent = permissionDtoMap.get(pid);
                parent.getChildren().add(child);
            }
        }
        // 保存树形结构
        session.setAttribute("rootPermission", root);
        return success(dbUser);
    }

    /**
     * 执行登录
     *
     * @return
     */
    @PostMapping("/doLogin")
    public String doLogin(User user, Model model) {
        /*
         * colg [获取表单数据]
         *  1. 获取表单数据
         *      1). HttpServletRequest
         *      2). 在方法参数列表中增加表单对应的参数，名称相同
         *      3). 将表单数据封装为实体类对象
         *  2. 查询用户信息
         *  3. 判断用户信息是否存在
         */
        User dbUser = userService.findUser(user);
        if (dbUser != null) {
            // 登录成功，跳转到主页
            return "main";
        } else {
            // 登录失败，跳转回到登录页面，提示错误信息
            String errorMsg = "登录帐号或密码不能正确，请重新输入";
            model.addAttribute("errorMsg", errorMsg);
            return "redirect:login";
        }
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
