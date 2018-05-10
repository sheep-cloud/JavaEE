package cn.colg.mapper;

import cn.colg.bean.Employee;
import tk.mybatis.mapper.common.Mapper;

/**
 * 员工Mapper
 * 
 * <pre>
 * 具体操作数据库的Mapper接口，需要继承通用Mapper提供的核心接口：Mapper<T>
 *  反省类型就是实体类的类型
 * </pre>
 *
 * @author colg
 */
public interface EmployeeMapper extends Mapper<Employee> {

}
