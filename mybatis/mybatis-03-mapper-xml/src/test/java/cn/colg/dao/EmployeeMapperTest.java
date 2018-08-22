package cn.colg.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.colg.BaseMapperTest;
import cn.colg.entity.Employee;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;

/**
 * 员工Mapper 测试
 *
 * @author colg
 */
@Slf4j
public class EmployeeMapperTest extends BaseMapperTest {

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#queryByLastNameResultMap(java.lang.String)}.
     */
    @Test
    public void testQueryByLastNameResultMap() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Map<Integer, Employee> map = employeeMapper.queryByLastNameResultMap("a");
        Console.log(JSON.toJSONString(map, true));
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findByIdResultMap(java.lang.Integer)}.
     */
    @Test
    public void testFindByIdResultMap() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Map<String, Object> map = employeeMapper.findByIdResultMap(1);
        Console.log(JSON.toJSONString(map, true));
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#queryByLastName(java.lang.String)}.
     */
    @Test
    public void testQueryByLastName() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryByLastName("jack");
        log.info("EmployeeMapperTest.testQueryByLastName() >> list : {}", list);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findByMap(java.util.Map)}.
     */
    @Test
    public void testFindByMap() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Dict map = new Dict(4);
        map.set("tableName", "tbl_employee")
           .set("lastName", "jack")
           .set("id", 1);
        Employee employee = employeeMapper.findByMap(map);
        log.info("EmployeeMapperTest.testFindByMap() >> employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findByIdAndLastName(java.lang.Integer, java.lang.String)}.
     */
    @Test
    public void testFindByIdAndLastName() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findByIdAndLastName(1, "jack");
        log.info("EmployeeMapperTest.testFindByIdAndLastName() >> employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findById(1);
        log.info("EmployeeMapperTest.testFindById() >> employee : {}", employee);
    }
    
    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findLastNameById(java.lang.Integer)}.
     */
    @Test
    public void testFindLastNameById() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        String lastName = employeeMapper.findLastNameById(1);
        log.info("EmployeeMapperTest.testFindLastNameById() >> lastName : {}", lastName);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#addEmployee(cn.colg.entity.Employee)}.
     */
    @Test
    public void testAddEmployee() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee(null, "rose", "1", "rose@colg.com");
        employeeMapper.addEmployee(employee);
        
        // 不是直接得到返回的主键id，而是通过之前的对象get出来
        log.info("EmployeeMapperTest.testAddEmployee() >> employee.getId() : {}", employee.getId());
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#updateEmployee(cn.colg.entity.Employee)}.
     */
    @Test
    public void testUpdateEmployee() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryByLastName("rose");
        Employee employee = list.stream().findFirst().get();
        
        if (employee != null) {
            Integer integer = employeeMapper.updateEmployee(employee.setLastName("Junit-rose"));
            log.info("EmployeeMapperTest.testUpdateEmployee() >> integer : {}", integer);
        }
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#deleteById(java.lang.Integer)}.
     */
    @Test
    public void testDeleteById() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryByLastName("rose");
        Employee employee = list.stream().findFirst().get();
        
        if (employee != null) {
            Boolean boolean1 = employeeMapper.deleteById(employee.getId());
            log.info("EmployeeMapperTest.testDeleteById() >> boolean1 : {}", boolean1);
        }
    }

    @Test
    public void testList() throws Exception {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<String> ids = employeeMapper.selectIds();
        log.info("EmployeeMapperTest.testList() >> ids : {}", ids);
        
        List<String> lastNames = employeeMapper.selectLastNamesByIds(ids);
        log.info("EmployeeMapperTest.testList() >> lastNames : {}", lastNames);
    }
    
    @Test
    public void testQueryByLastNameResultDict() throws Exception {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Dict> list = employeeMapper.queryByLastNameResultDict("a");
        Console.log(JSON.toJSONString(list, true));
    }
}
