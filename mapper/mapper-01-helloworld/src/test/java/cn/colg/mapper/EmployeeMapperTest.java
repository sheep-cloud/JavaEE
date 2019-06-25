package cn.colg.mapper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.BaseMapperTest;
import cn.colg.bean.Employee;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

/**
 * 员工Mapper 测试
 *
 * @author colg
 */
@Slf4j
public class EmployeeMapperTest extends BaseMapperTest {

    @Autowired
    private EmployeeMapper mapper;

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectOneMapper#selectOne(java.lang.Object)}.
     */
    @Test
    public final void testSelectOne() {
        Employee employee = mapper.selectOne(new Employee("tom", 1254.37));
        log.info("员工信息: {}", employee);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectMapper#select(java.lang.Object)}.
     */
    @Test
    public final void testSelect() {
        List<Employee> employeeList = mapper.select(new Employee("tom", 1254.37));
        log.info("员工数量: {}", employeeList.size());
        log.info("员工列表: {}", employeeList);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectAllMapper#selectAll()}.
     */
    @Test
    public final void testSelectAll() {
        List<Employee> employeeList = mapper.selectAll();
        log.info("员工数量: {}", employeeList.size());
        log.info("员工列表: {}", employeeList);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectCountMapper#selectCount(java.lang.Object)}.
     */
    @Test
    public final void testSelectCount() {
        int count = mapper.selectCount(new Employee("tom", 1254.37));
        log.info("员工数量: {}", count);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper#selectByPrimaryKey(java.lang.Object)}.
     */
    @Test
    public final void testSelectByPrimaryKey() {
        Employee employee = mapper.selectByPrimaryKey("f5dccdca658f11e986ca54ee75c6aeb0");
        log.info("员工信息: {}", employee);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.ExistsWithPrimaryKeyMapper#existsWithPrimaryKey(java.lang.Object)}.
     */
    @Test
    public final void testExistsWithPrimaryKey() {
        boolean exists = mapper.existsWithPrimaryKey("f5dccdca658f11e986ca54ee75c6aeb0_");
        log.info("员工存在: {}", exists);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.insert.InsertMapper#insert(java.lang.Object)}.
     */
    @Test
    public final void testInsert() {
        Employee employee = new Employee("jack", 9999.99);
        int result = mapper.insert(employee);
        log.info("新增状态: {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper#insertSelective(java.lang.Object)}.
     */
    @Test
    public final void testInsertSelective() {
        Employee employee = new Employee("rose", 8888.88);
        int result = mapper.insertSelective(employee);
        log.info("新增状态: {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper#updateByPrimaryKey(java.lang.Object)}.
     */
    @Test
    public final void testUpdateByPrimaryKey() {
        String empId = "018c545565454c7582127af5d462827c";
        Employee employee = new Employee("jack", 9999.88).setEmpId(empId);
        int result = mapper.updateByPrimaryKey(employee);
        log.info("修改状态: {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper#updateByPrimaryKeySelective(java.lang.Object)}.
     */
    @Test
    public final void testUpdateByPrimaryKeySelective() {
        String empId = "35e1238ac7a347e3ac8ffccef11099a8";
        Employee employee = new Employee("rose", 8888.77).setEmpId(empId);
        int result = mapper.updateByPrimaryKeySelective(employee);
        log.info("修改状态: {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.delete.DeleteMapper#delete(java.lang.Object)}.
     */
    @Test
    public final void testDelete() {
        Employee employee = new Employee("justin", 4203.15000);
        int result = mapper.delete(employee);
        log.info("删除状态: {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper#deleteByPrimaryKey(java.lang.Object)}.
     */
    @Test
    public final void testDeleteByPrimaryKey() {
        int result = mapper.deleteByPrimaryKey("45f68c067cdb11e8a2c454ee75c6aeb0");
        log.info("删除状态: {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.SelectByExampleMapper#selectByExample(java.lang.Object)}.
     */
    @Test
    public final void testSelectByExample() {
        Example example = new Example(Employee.class);
        example.createCriteria().andLike("empName", "%o%");
        List<Employee> employeeList = mapper.selectByExample(example);
        log.info("员工数量: {}", employeeList.size());
        log.info("员工列表: {}", employeeList);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.SelectOneByExampleMapper#selectOneByExample(java.lang.Object)}.
     */
    @Test
    public final void testSelectOneByExample() {
        Example example = new Example(Employee.class);
        example.createCriteria().andEqualTo("empName", "bob").andEqualTo("empSalary", 5560.11000);
        Employee employee = mapper.selectOneByExample(example);
        log.info("员工信息: {}", employee);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.SelectCountByExampleMapper#selectCountByExample(java.lang.Object)}.
     */
    @Test
    public final void testSelectCountByExample() {
        Example example = new Example(Employee.class);
        example.createCriteria().andLike("empName", "%o%");
        int count = mapper.selectCountByExample(example);
        log.info("员工数量: {}", count);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.DeleteByExampleMapper#deleteByExample(java.lang.Object)}.
     */
    @Test
    public final void testDeleteByExample() {
        Example example = new Example(Employee.class);
        example.createCriteria().andBetween("empSalary", 1000D, 3000D);
        int result = mapper.deleteByExample(example);
        log.info("删除状态: {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.UpdateByExampleMapper#updateByExample(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public final void testUpdateByExample() {
        Example example = new Example(Employee.class);
        example.createCriteria().andEqualTo("empName", "bob");
        int result = mapper.updateByExample(new Employee("bob", 5550.11000), example);
        log.info("修改状态: {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper#updateByExampleSelective(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public final void testUpdateByExampleSelective() {
        Example example = new Example(Employee.class);
        example.createCriteria().andEqualTo("empName", "bob");
        int result = mapper.updateByExampleSelective(new Employee("bob", 5550.11000), example);
        log.info("删除状态: {}", result);
    }
    
}
