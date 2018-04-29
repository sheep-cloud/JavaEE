package cn.colg.web;

import static cn.colg.bean.ResultBean.success;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.colg.bean.ResultBean;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 发现服务Controller
 *
 * @author colg
 */
@Api(tags = "发现服务管理")
@RestController
@RequestMapping("/discovery")
public class DiscoveryController {

    public static final Log log = LogFactory.get();

    /** 发现可用的服务 */
    @Autowired
    private DiscoveryClient discoveryClient;

    @ApiOperation("获取可用的服务")
    @GetMapping("/client")
    public ResultBean client() {
        List<String> services = discoveryClient.getServices();
        
        // 获取所有可用的服务
        services.stream()
                // 非空
                .filter(service -> service != null)
                // 遍历所有服务，获取服务名
                .forEach(service -> {
                    log.info(service);
                    
                    // 根据服务名获取所有 服务实例（服务名转大写，对应Eureka Application）
                    List<ServiceInstance> instances = discoveryClient.getInstances(service.toUpperCase());
                    // 遍历服务实例，打印具体信息
                    instances.forEach(instance -> log.info(JSON.toJSONString(instance)));
                });
        
        return success(this.discoveryClient);
    }

}
