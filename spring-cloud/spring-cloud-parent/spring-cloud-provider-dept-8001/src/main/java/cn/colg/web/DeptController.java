package cn.colg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.bean.ResultBean;
import cn.colg.entity.Dept;
import cn.colg.service.DeptService;
import static cn.colg.util.ResultUtil.success;

/**
 * 部门Controller
 *
 * @author colg
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("/add")
    public ResultBean add(@RequestBody Dept dept) {
        return success(deptService.add(dept));
    }

    @GetMapping("/get/{id}")
    public ResultBean get(@PathVariable Long id) {
        return success(deptService.get(id));
    }

    @GetMapping("/list")
    public ResultBean list() {
        return success(deptService.list());
    }
}
