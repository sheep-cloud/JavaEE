package cn.colg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.colg.bean.ResultBean;
import cn.colg.constant.RestUrlConst;

/**
 * 发现服务Controller 消费端
 *
 * @author colg
 */
@RestController
@RequestMapping("/consumer/discovery")
public class ConsumerDiscoveryController {

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/client")
    public ResultBean client() {
        return restTemplate.getForObject(RestUrlConst.DEPT_REST_URL_PREFIX + "/discovery/client", ResultBean.class);
    }
}
