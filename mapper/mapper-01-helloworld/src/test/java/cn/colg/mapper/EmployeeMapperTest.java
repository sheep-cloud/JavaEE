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
        log.info("EmployeeMapperTest.testSelectOne() >> employee : {}", employee);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectMapper#select(java.lang.Object)}.
     */
    @Test
    public final void testSelect() {
        List<Employee> employeeList = mapper.select(new Employee("tom", 1254.37));
        log.info("EmployeeMapperTest.testSelect() >> employeeList.size() : {}", employeeList.size());
        employeeList.forEach(employee -> {
            log.info("EmployeeMapperTest.testSelect() >> employee : {}", employee);
        });
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectAllMapper#selectAll()}.
     */
    @Test
    public final void testSelectAll() {
        List<Employee> employeeList = mapper.selectAll();
        log.info("EmployeeMapperTest.testSelect() >> employeeList.size() : {}", employeeList.size());
        employeeList.forEach(employee -> {
            log.info("EmployeeMapperTest.testSelect() >> employee : {}", employee);
        });
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectCountMapper#selectCount(java.lang.Object)}.
     */
    @Test
    public final void testSelectCount() {
        int count = mapper.selectCount(new Employee("tom", 1254.37));
        log.info("EmployeeMapperTest.testSelectCount() >> count : {}", count);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper#selectByPrimaryKey(java.lang.Object)}.
     */
    @Test
    public final void testSelectByPrimaryKey() {
        Employee employee = mapper.selectByPrimaryKey("8a4864ae7a7f11e8ad9154ee75c6aeb0");
        log.info("EmployeeMapperTest.testSelectByPrimaryKey() >> employee : {}", employee);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.ExistsWithPrimaryKeyMapper#existsWithPrimaryKey(java.lang.Object)}.
     */
    @Test
    public final void testExistsWithPrimaryKey() {
        boolean exists = mapper.existsWithPrimaryKey("8a4864ae7a7f11e8ad9154ee75c6aeb0");
        log.info("EmployeeMapperTest.testExistsWithPrimaryKey() >> exists : {}", exists);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.insert.InsertMapper#insert(java.lang.Object)}.
     */
    @Test
    public final void testInsert() {
        Employee employee = new Employee("jack", 9999.99);
        mapper.insert(employee);
        log.info("EmployeeMapperTest.testInsert() >> employee.getEmpId() : {}", employee.getEmpId());
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper#insertSelective(java.lang.Object)}.
     */
    @Test
    public final void testInsertSelective() {
        Employee employee = new Employee("rose", 8888.88);
        mapper.insertSelective(employee);
        log.info("EmployeeMapperTest.testInsert() >> employee.getEmpId() : {}", employee.getEmpId());
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper#updateByPrimaryKey(java.lang.Object)}.
     */
    @Test
    public final void testUpdateByPrimaryKey() {
        Employee employee = new Employee("jack", 9999.88).setEmpId("b88e654b9d3c4f0db8b54db7874784d8");
        mapper.updateByPrimaryKey(employee);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper#updateByPrimaryKeySelective(java.lang.Object)}.
     */
    @Test
    public final void testUpdateByPrimaryKeySelective() {
        Employee employee = new Employee("rose", 8888.88).setEmpId("f62134b196864a63bdf63194bc9c80d7");
        mapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.delete.DeleteMapper#delete(java.lang.Object)}.
     */
    @Test
    public final void testDelete() {
        Employee employee = new Employee("justin", 4203.15000);
        int delete = mapper.delete(employee);
        log.info("EmployeeMapperTest.testDelete() >> delete : {}", delete);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper#deleteByPrimaryKey(java.lang.Object)}.
     */
    @Test
    public final void testDeleteByPrimaryKey() {
        int result = mapper.deleteByPrimaryKey("45f68c067cdb11e8a2c454ee75c6aeb0");
        log.info("EmployeeMapperTest.testDeleteByPrimaryKey() >> result : {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.SelectByExampleMapper#selectByExample(java.lang.Object)}.
     */
    @Test
    public final void testSelectByExample() {
        Example example = new Example(Employee.class);
        example.createCriteria().andLike("empName", "%o%");
        List<Employee> employeeList = mapper.selectByExample(example);
        log.info("EmployeeMapperTest.testSelectByExample() >> 员工数量 : {}", employeeList.size());
        log.info("EmployeeMapperTest.testSelectByExample() >> 员工列表 : {}", employeeList);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.SelectOneByExampleMapper#selectOneByExample(java.lang.Object)}.
     */
    @Test
    public final void testSelectOneByExample() {
        Example example = new Example(Employee.class);
        example.createCriteria().andEqualTo("empName", "bob").andEqualTo("empSalary", 5560.11000);
        Employee employee = mapper.selectOneByExample(example);
        log.info("EmployeeMapperTest.testSelectOneByExample() >> employee : {}", employee);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.SelectCountByExampleMapper#selectCountByExample(java.lang.Object)}.
     */
    @Test
    public final void testSelectCountByExample() {
        Example example = new Example(Employee.class);
        example.createCriteria().andLike("empName", "%o%");
        int count = mapper.selectCountByExample(example);
        log.info("EmployeeMapperTest.testSelectCountByExample() >> count : {}", count);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.DeleteByExampleMapper#deleteByExample(java.lang.Object)}.
     */
    @Test
    public final void testDeleteByExample() {
        Example example = new Example(Employee.class);
        example.createCriteria().andBetween("empSalary", 1000D, 3000D);
        int result = mapper.deleteByExample(example);
        log.info("EmployeeMapperTest.testDeleteByExample() >> result : {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.UpdateByExampleMapper#updateByExample(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public final void testUpdateByExample() {
        Example example = new Example(Employee.class);
        example.createCriteria().andEqualTo("empName", "bob");
        int result = mapper.updateByExample(new Employee("bob", 5550.11000), example);
        log.info("EmployeeMapperTest.testUpdateByExample() >> result : {}", result);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper#updateByExampleSelective(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public final void testUpdateByExampleSelective() {
        Example example = new Example(Employee.class);
        example.createCriteria().andEqualTo("empName", "bob");
        int result = mapper.updateByExampleSelective(new Employee("bob", 5550.11000), example);
        log.info("EmployeeMapperTest.testUpdateByExample() >> result : {}", result);
    }

}
