package cn.colg.web;

import static cn.colg.bean.ResultBean.success;

import java.util.List;

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

    /**
     * '@HystrixCommand(fallbackMethod = "processHystrixGet")'：</br>
     * 一旦调用服务方法失败并抛出了异常信息后，会自动调用@HystrixCommand标注的fallbackMethod调用类中的指定方法 => {@link #processHystrixGet}
     * 
     * @param id
     * @return
     */
    @ApiOperation("根据id获取部门")
    @ApiImplicitParam(name = "id", value = "主键", paramType = "Long", required = true)
    @HystrixCommand(fallbackMethod = "processHystrixGet")
    @GetMapping("/get/{id}")
    public ResultBean get(@PathVariable Long id) {
        Dept dept = deptService.get(id);
        if (dept == null) {
            throw new RuntimeException("该ID： [" + id + "] 没有找到对应的信息！");
        }
        return success(dept);
    }

    /**
     * 指定处理备用逻辑的方法。 <br>
     * 应该在HystrixCommand的同一类中定义回退方法。 <br>
     * 另外，回退方法应该与作为hystrix命令调用的方法具有相同的签名。
     *
     * @param id
     * @return
     */
    public ResultBean processHystrixGet(@PathVariable Long id) {
        Dept dept = new Dept(id, "该ID： [" + id + "] 没有找到对应的信息！--@HystrixCommand", "no this database in MySQL");
        return success(dept);
    }

    @ApiOperation("获取所有部门")
    @GetMapping("/list")
    public ResultBean list() {
        List<Dept> list = deptService.list();
        return success(list);
    }

}
