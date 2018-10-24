package cn.colg.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.colg.BaseMapperTest;
import cn.colg.entity.Employee;
import cn.hutool.core.collection.CollUtil;
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
        log.info("map : {}", JSON.toJSONString(map));
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findByIdResultMap(java.lang.Integer)}.
     */
    @Test
    public void testFindByIdResultMap() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Map<String, Object> map = employeeMapper.findByIdResultMap(1);
        log.info("map : {}", JSON.toJSONString(map));
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#queryByLastName(java.lang.String)}.
     */
    @Test
    public void testQueryByLastName() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryByLastName("a");
        log.info("list : {}", list);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findByMap(java.util.Map)}.
     */
    @Test
    public void testFindByMap() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Dict map = Dict.create().set("tableName", "tbl_employee")
                                .set("lastName", "jack")
                                .set("id", 1);
        Employee employee = employeeMapper.findByMap(map);
        log.info("employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findByIdAndLastName(java.lang.Integer, java.lang.String)}.
     */
    @Test
    public void testFindByIdAndLastName() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findByIdAndLastName(1, "jack");
        log.info("employee : {}", employee);
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findById(java.lang.Integer)}.
     */
    @Test
    public void testFindById() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.findById(1);
        log.info("employee : {}", employee);
    }
    
    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#findLastNameById(java.lang.Integer)}.
     */
    @Test
    public void testFindLastNameById() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        String lastName = employeeMapper.findLastNameById(1);
        log.info("lastName : {}", lastName);
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
        log.info("employee.getId() : {}", employee.getId());
    }
    
    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#addEmployee2(cn.colg.entity.Employee)}.
     */
    @Test
    public void testAddEmployee2() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee(null, "rose", "1", "rose@colg.com");
        employeeMapper.addEmployee2(employee);
        
        // 不是直接得到返回的主键id，而是通过之前的对象get出来
        log.info("employee.getId() : {}", employee.getId());
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#updateEmployee(cn.colg.entity.Employee)}.
     */
    @Test
    public void testUpdateEmployee() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryByLastName("rose");
        Employee employee = CollUtil.getFirst(list);
        
        if (employee != null) {
            log.info("employee : {}", employee);
            
            Integer integer = employeeMapper.updateEmployee(employee.setLastName("Junit-rose"));
            log.info("integer : {}", integer);
        }
    }

    /**
     * Test method for {@link cn.colg.dao.EmployeeMapper#deleteById(java.lang.Integer)}.
     */
    @Test
    public void testDeleteById() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> list = employeeMapper.queryByLastName("rose");
        Employee employee = CollUtil.getFirst(list);
        
        if (employee != null) {
            log.info("employee : {}", employee);
            
            Boolean bool = employeeMapper.deleteById(employee.getId());
            log.info("bool : {}", bool);
        }
    }

    @Test
    public void testList() throws Exception {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<String> ids = employeeMapper.selectIds();
        log.info("ids : {}", ids);
        
        List<String> lastNames = employeeMapper.selectLastNamesByIds(ids);
        log.info("lastNames : {}", lastNames);
    }
    
    @Test
    public void testQueryByLastNameResultDict() throws Exception {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Dict> list = employeeMapper.queryByLastNameResultDict("a");
        log.info("list : {}", JSON.toJSONString(list));
    }
}
