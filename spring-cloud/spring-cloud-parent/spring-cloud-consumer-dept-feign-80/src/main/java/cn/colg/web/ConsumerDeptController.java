package cn.colg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.bean.ResultBean;
import cn.colg.entity.Dept;
import cn.colg.service.DeptClientService;

/**
 * 部门Controller 消费者
 *
 * @author colg
 */
@RestController
@RequestMapping("/consumer/dept")
public class ConsumerDeptController {

    @Autowired
    private DeptClientService deptClientService;

    @GetMapping("/get/{id}")
    public ResultBean get(@PathVariable Long id) {
        return deptClientService.get(id);
    }

    @GetMapping("/list")
    public ResultBean list() {
        return deptClientService.list();
    }

    @PostMapping("/add")
    public ResultBean add(Dept dept) {
        return deptClientService.add(dept);
    }
}
