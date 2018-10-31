package cn.colg.controller;

import static cn.colg.util.ResultVoUtil.fail;
import static cn.colg.util.ResultVoUtil.success;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.vo.ResultVo;
import cn.hutool.core.collection.CollUtil;

/**
 * HelloController
 *
 * @author colg
 */
@RestController
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/add")
    public ResultVo add() {
        String sql = "INSERT INTO department(departmentName) VALUES('技术部')";
        int update = jdbcTemplate.update(sql);
        return success(update);
    }

    @GetMapping("/query")
    public ResultVo map() {
        String sql = "SELECT * FROM department";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        if (CollUtil.isEmpty(list)) {
            return fail(1, "数据库里没有数据！");
        }
        return success(list);
    }
}
