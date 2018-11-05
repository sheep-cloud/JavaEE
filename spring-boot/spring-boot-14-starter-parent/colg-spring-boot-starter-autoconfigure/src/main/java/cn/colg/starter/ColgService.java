package cn.colg.starter;

/**
 * ColgService
 *
 * @author colg
 */
public class ColgService {

    private ColgProperties colgProperties;

    public String sayHello(String name) {
        return colgProperties.getPrefix() + "-" + name + "-" + colgProperties.getSuffix();
    }

    public ColgProperties getColgProperties() {
        return colgProperties;
    }

    public void setColgProperties(ColgProperties colgProperties) {
        this.colgProperties = colgProperties;
    }

}
