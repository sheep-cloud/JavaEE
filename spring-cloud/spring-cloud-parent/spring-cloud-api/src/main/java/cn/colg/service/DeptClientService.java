package cn.colg.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.colg.bean.ResultBean;
import cn.colg.entity.Dept;
import cn.colg.fallback.DeptClientServiceFallback;

/**
 * 部门Service - 客户端
 * 
 * <pre>
 * `fallbackFactory = DeptClientServiceFallback.class`： 一旦返回异常信息，将进入 {@link cn.colg.fallback.DeptClientServiceFallback}，使用备用逻辑
 * </pre>
 *
 * @author colg
 */
@FeignClient(value = "SPRING-CLOUD-DEPT", fallbackFactory = DeptClientServiceFallback.class)
@RequestMapping("/dept")
public interface DeptClientService {

    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    ResultBean add(Dept dept);

    /**
     * 根据id获取部门
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    ResultBean get(@PathVariable("id") Long id);

    /**
     * 获取所有部门
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    ResultBean list();
}
