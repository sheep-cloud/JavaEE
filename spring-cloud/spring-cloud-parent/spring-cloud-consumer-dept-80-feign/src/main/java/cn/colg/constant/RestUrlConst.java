package cn.colg.constant;

/**
 * rest url 常量类
 *
 * @author colg
 */
public final class RestUrlConst {

    private RestUrlConst() {}

    /**
     * 部门服务 restTemplate (http://ip:port)
     * 
     * @deprecated 2018-04-30 此常量已经启用，使用ribbon性能更好，{@link #DEPT_RIBBON_URL_PREFIX}
     */
    @Deprecated
    public static final String DEPT_REST_TEMPLATE_URL_PREFIX = "http://localhost:8001";

    /** 部门服务 ribbon (http://Application) */
    public static final String DEPT_RIBBON_URL_PREFIX = "http://SPRING-CLOUD-DEPT";

}
