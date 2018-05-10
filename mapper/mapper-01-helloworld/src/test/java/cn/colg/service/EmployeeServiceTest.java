package cn.colg.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.colg.BaseMapperTest;
import cn.colg.bean.Employee;
import lombok.extern.slf4j.Slf4j;

/**
 * 员工Service 测试
 *
 * @author colg
 */
@Slf4j
public class EmployeeServiceTest extends BaseMapperTest {
    
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectOneMapper#selectOne(java.lang.Object)}.
     */
    @Test
    public void testSelectOne() {
        Employee employee = new Employee(null, "bob", 5560.11, null);
        Employee employeeResult = employeeService.selectOne(employee);
        log.info("EmployeeServiceTest.testSelectOne() >> employeeResult : {}", employeeResult);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper#selectByPrimaryKey(java.lang.Object)}.
     */
    @Test
    public void testSelectByPrimaryKey() {
        Integer empId = 3;
        Employee employee = employeeService.selectByPrimaryKey(empId);
        log.info("EmployeeServiceTest.testExistsWithPrimaryKey() >> employee : {}", employee);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.select.ExistsWithPrimaryKeyMapper#existsWithPrimaryKey(java.lang.Object)}.
     */
    @Test
    public void testExistsWithPrimaryKey() {
        Integer empId = 33;
        Boolean existed = employeeService.existsWithPrimaryKey(empId);
        log.info("EmployeeServiceTest.testExistsWithPrimaryKey() >> employee : {}", existed);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.insert.InsertMapper#insert(java.lang.Object)}.
     */
    @Test
    public void testInsert() {
        Employee employee = new Employee(null, "emp01", 9000.00, 23);
        Integer empId = employeeService.insert(employee);
        log.info("EmployeeServiceTest.testInsert() >> empId : {}", empId);
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper#insertSelective(java.lang.Object)}.
     */
    @Test
    public void testInsertSelective() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper#updateByPrimaryKey(java.lang.Object)}.
     */
    @Test
    public void testUpdateByPrimaryKey() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper#updateByPrimaryKeySelective(java.lang.Object)}.
     */
    @Test
    public void testUpdateByPrimaryKeySelective() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.delete.DeleteMapper#delete(java.lang.Object)}.
     */
    @Test
    public void testDelete() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper#deleteByPrimaryKey(java.lang.Object)}.
     */
    @Test
    public void testDeleteByPrimaryKey() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.SelectByExampleMapper#selectByExample(java.lang.Object)}.
     */
    @Test
    public void testSelectByExample() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.SelectOneByExampleMapper#selectOneByExample(java.lang.Object)}.
     */
    @Test
    public void testSelectOneByExample() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.SelectCountByExampleMapper#selectCountByExample(java.lang.Object)}.
     */
    @Test
    public void testSelectCountByExample() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.DeleteByExampleMapper#deleteByExample(java.lang.Object)}.
     */
    @Test
    public void testDeleteByExample() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.UpdateByExampleMapper#updateByExample(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testUpdateByExample() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper#updateByExampleSelective(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testUpdateByExampleSelective() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link tk.mybatis.mapper.common.rowbounds.SelectByExampleRowBoundsMapper#selectByExampleAndRowBounds(java.lang.Object, org.apache.ibatis.session.RowBounds)}
     * .
     */
    @Test
    public void testSelectByExampleAndRowBounds() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link tk.mybatis.mapper.common.rowbounds.SelectRowBoundsMapper#selectByRowBounds(java.lang.Object, org.apache.ibatis.session.RowBounds)}
     * .
     */
    @Test
    public void testSelectByRowBounds() {
        fail("Not yet implemented"); // TODO
    }
}
