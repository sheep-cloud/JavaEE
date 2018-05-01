package cn.colg.web;

import static cn.colg.bean.ResultBean.success;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 部门Controller
 *
 * @author colg
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/dept")
public class DeptController {
    
    @Autowired
    private DeptService deptService;

    @ApiOperation("添加部门")
    @ApiImplicitParam(name = "dept", value = "部门Entity", dataType = "Dept")
    @PostMapping("/add")
    public ResultBean add(@RequestBody Dept dept) {
        return success(deptService.add(dept));
    }

    @ApiOperation("根据id获取部门")
    @ApiImplicitParam(name = "id", value = "主键", paramType = "Long", required = true)
    @GetMapping("/get/{id}")
    public ResultBean get(@PathVariable Long id) {
        return success(deptService.get(id));
    }

    @ApiOperation("获取所有部门")
    @GetMapping("/list")
    public ResultBean list() {
        return success(deptService.list());
    }
    
}
