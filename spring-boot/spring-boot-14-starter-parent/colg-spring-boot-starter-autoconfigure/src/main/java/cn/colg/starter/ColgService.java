package cn.colg.starter;

import lombok.Getter;
import lombok.Setter;

/**
 * ColgService
 *
 * @author colg
 */
@Getter
@Setter
public class ColgService {

    private ColgProperties colgProperties;

    public String sayHello(String name) {
        return colgProperties.getPrefix() + "-" + name + "-" + colgProperties.getSuffix();
    }

}
