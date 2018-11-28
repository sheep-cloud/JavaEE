package cn.colg.util;

import java.util.HashMap;

import com.cloopen.rest.sdk.CCPRestSDK;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.dialect.Props;
import lombok.extern.slf4j.Slf4j;

/**
 * 短信工具
 *
 * @author colg
 */
@Slf4j
public class SmsUtil {

    private static Props props = null;

    static {
        props = new Props("sms.properties", CharsetUtil.UTF_8);
        log.info("props: {}", props);
    }

    /**
     * 发送短信请求
     *
     * @param phone 必选参数 短信接收端手机号码集合，用英文逗号分开，每批发送的手机号数量不得超过100个
     * @param code 短信验证码
     * @return
     * @author colg
     */
    public static boolean sendCode(String phone, String code) {
        HashMap<String, Object> result = null;

        CCPRestSDK restAPI = new CCPRestSDK();

        // 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
        restAPI.init(props.getStr("Rsst_URL"), props.getStr("Rest_Prot"));
        // 初始化主帐号和主帐号TOKEN
        restAPI.setAccount(props.getStr("ACCOUNT_SID"), props.getStr("AUTH_TOKEN"));
        // 初始化应用ID
        restAPI.setAppId(props.getStr("AppID"));
        // 发送短信模板请求
        result = restAPI.sendTemplateSMS(phone, "1", new String[] {code, "1"});
        
        return ("000000".equals(result.get("statusCode")));
    }
}
