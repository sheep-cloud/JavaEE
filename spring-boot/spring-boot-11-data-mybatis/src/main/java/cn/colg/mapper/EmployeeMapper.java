package cn.colg.mapper;

import java.util.List;

import cn.colg.bean.Employee;

/**
 * EmployeeMapper；配置版
 * 
 * <pre>
 * `@MapperScan`: 使用MapperScan在启动类批量扫描
 * </pre>
 *
 * @author colg
 */
public interface EmployeeMapper {
    
    List<Employee> query();

    Employee getEmpById(Integer id);
    
    void insertEmp(Employee employee);
}
