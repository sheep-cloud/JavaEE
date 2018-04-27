package cn.colg.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.SpringCloudProviderDept8001ApplicationTest;
import cn.colg.entity.Dept;

/**
 * 部门Mapper 测试
 *
 * @author colg
 */
public class DeptMapperTest extends SpringCloudProviderDept8001ApplicationTest {

    public static final String DATA_PREFIX = "Junit_Test_";

    @Autowired
    private DeptMapper deptMapper;

    @Test
    public void testAddDept() {
        Dept dept = new Dept();
        dept.setDeptName(DATA_PREFIX + "前端部");
        boolean existed = deptMapper.addDept(dept);
        assertTrue(existed);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Dept dept = deptMapper.findById(id);
        assertEquals(id, dept.getDeptNo());
        assertEquals("开发部", dept.getDeptName());
    }

    @Test
    public void testQueryAll() {
        List<Dept> list = deptMapper.queryAll();
        assertNotNull(list);
        assertTrue(list.size() >= 1);
    }

}
