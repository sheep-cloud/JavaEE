package cn.colg.web;

import static cn.colg.bean.ResultBean.success;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.colg.bean.ResultBean;
import cn.colg.entity.Dept;
import cn.colg.service.DeptService;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
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

    public static final Log log = LogFactory.get();

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
    // 一旦调用服务方法失败并抛出了异常信息后，会自动调用@HystrixCommand标注的fallbackMethod调用类中的指定方法
    @HystrixCommand(fallbackMethod = "processHystrixGet")
    @GetMapping("/get/{id}")
    public ResultBean get(@PathVariable Long id) {
        Dept dept = deptService.get(id);
        if (dept == null) {
            throw new RuntimeException("该ID：[ " + id + "]没有找到对应的信息！");
        }
        return success(dept);
    }

    public ResultBean processHystrixGet(@PathVariable Long id) {
        Dept dept = new Dept(id, "该ID： [" + id + "]没有找到对应的信息！--@HystrixCommand", "no this database in MySQL");
        return success(dept);
    }

    @ApiOperation("获取所有部门")
    @GetMapping("/list")
    public ResultBean list() {
        return success(deptService.list());
    }

}
