package cn.colg.sdk;

import com.cloopen.rest.sdk.CCPRestSDK;

import cn.hutool.setting.dialect.Props;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 云通讯SDK数据源
 *
 * @author colg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class CCPRestDS {

    /** 默认配置文件 */
    public static final String CCPREST_CONFIG_PATH = "config/ccprest.properties";

    /** 云通讯SDK数据源，单例（Eager） */
    private static CCPRestDS ccpRestDS = new CCPRestDS();

    /** 云通讯sdk，单例（Lazy） */
    private static volatile CCPRestSDK sdk = null;

    /**
     * 创建CCPRestDS
     *
     * @return
     * @author colg
     */
    public static CCPRestDS create() {
        return ccpRestDS;
    }

    /**
     * 获取CCPRestSDK
     *
     * @return
     * @author colg
     */
    public CCPRestSDK getSdk() {
        return init(null);
    }

    /**
     * 获取CCPRestSDK
     *
     * @param path CCPRestSDK 配置文件路径
     * @return
     * @author colg
     */
    public CCPRestSDK getSdk(String path) {
        return init(path);
    }

    /**
     * 初始化CCPRestSDK
     *
     * @param path CCPRestSDK 配置文件路径
     * @return
     * @author colg
     */
    private CCPRestSDK init(String path) {
        // 双重检查
        if (sdk == null) {
            synchronized (CCPRestSDK.class) {
                if (sdk == null) {
                    log.info("初始化CCPRestSDK");
                    Props props;
                    if (null == path) {
                        props = new Props(CCPREST_CONFIG_PATH);
                    } else {
                        props = new Props(path);
                    }
                    sdk = new CCPRestSDK();
                    // 初始化服务地址和端口; 服务器地址不需要写https://
                    sdk.init(props.getStr("Rsst_URL"), props.getStr("Rest_Prot"));
                    // 初始化主帐号信息
                    sdk.setAccount(props.getStr("ACCOUNT_SID"), props.getStr("AUTH_TOKEN"));
                    // 初始化应用Id
                    sdk.setAppId(props.getStr("AppID"));
                }
            }
        }

        return sdk;
    }
}
