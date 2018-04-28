package cn.colg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.colg.bean.ResultBean;
import cn.colg.constant.RestUrlConst;
import cn.colg.entity.Dept;

/**
 * 部门Controller 消费者
 *
 * @author colg
 */
@RestController
@RequestMapping("/consumer/dept")
public class ConsumerDeptController {

    /**
     * RestTemplate 提供了多种便捷访问远程Http服务的方法；</br>
     * 
     * 是一种简单便捷的访问restful服务模版类，是Spring提供的用于访问rest服务的客户端模版工具集
     * 
     * <pre>
     * 使用：
     *  使用restTemplate访问restful接口非常的简单粗暴无脑。
     *  (url, resultMap, ResponseBean.class);
     *  REST请求地址，请求参数，HTTP响应被转换成的对象类型。
     * </pre>
     */
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get/{id}")
    public ResultBean get(@PathVariable Long id) {
        return restTemplate.getForObject(RestUrlConst.DEPT_REST_URL_PREFIX + "/dept/get/" + id, ResultBean.class);
    }

    @GetMapping("/list")
    public ResultBean list() {
        return restTemplate.getForObject(RestUrlConst.DEPT_REST_URL_PREFIX + "/dept/list", ResultBean.class);
    }

    @PostMapping("/add")
    public ResultBean add(Dept dept) {
        return restTemplate.postForObject(RestUrlConst.DEPT_REST_URL_PREFIX + "/dept/add", dept, ResultBean.class);
    }
}
