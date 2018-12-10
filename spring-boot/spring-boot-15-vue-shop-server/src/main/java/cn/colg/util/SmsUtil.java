package cn.colg.util;

import java.util.HashMap;

import com.cloopen.rest.sdk.CCPRestSDK;

import cn.colg.sdk.CCPRestDS;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 短信工具
 *
 * @author colg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SmsUtil {

    /**
     * 发送短信请求
     *
     * @param phone 必选参数 短信接收端手机号码集合，用英文逗号分开，每批发送的手机号数量不得超过100个
     * @param code 短信验证码
     * @return
     * @author colg
     */
    public static boolean sendCode(String phone, String code) {
        // 获取云通讯sdk
        CCPRestSDK sdk = CCPRestDS.create().getSdk();
        // 发送短信模板请求
        HashMap<String, Object> result = sdk.sendTemplateSMS(phone, "1", new String[] {code, "1"});
        // 判断是否发送成功
        return ("000000".equals(result.get("statusCode")));
    }
}
