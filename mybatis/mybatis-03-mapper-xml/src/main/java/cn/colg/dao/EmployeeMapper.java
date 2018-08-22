package cn.colg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import cn.colg.entity.Employee;
import cn.hutool.core.lang.Dict;

/**
 * 员工Mapper
 *
 * @author colg
 */
public interface EmployeeMapper {

    /**
     * 根据姓名模糊查询员工列表，并封装到map里中；key：主键，value：员工
     * 
     * <pre>
     * '@MapKey("id")'：告诉Mybatis封装这个map的时候使用哪个属性作为map的key
     * </pre>
     *
     * @param lastName
     * @return
     */
    @MapKey("id")
    Map<Integer, Employee> queryByLastNameResultMap(@Param("lastName") String lastName);

    /**
     * 根据id查询一条记录，并封装到map里；key：列名，value：值
     *
     * @param id
     * @return
     */
    Map<String, Object> findByIdResultMap(@Param("id") Integer id);

    /**
     * 根据姓名模糊查询员工列表
     *
     * @param lastName
     * @return
     */
    List<Employee> queryByLastName(@Param("lastName") String lastName);

    /**
     * 根据map查询一个员工
     *
     * @param map
     * @return
     */
    Employee findByMap(@Param("map") Map<String, Object> map);

    /**
     * 根据id和姓名查询员工
     *
     * @param id
     * @param lastName
     * @return
     */
    Employee findByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    Employee findById(@Param("id") Integer id);

    /**
     * 根据id查询员工姓名
     *
     * @param id
     * @return
     */
    String findLastNameById(@Param("id") Integer id);

    /**
     * 新增员工，返回主键
     *
     * @param employee
     * @return
     */
    Integer addEmployee(@Param("employee") Employee employee);

    /**
     * 修改员工，返回修改记录数
     *
     * @param employee
     * @return
     */
    Integer updateEmployee(@Param("employee") Employee employee);

    /**
     * 删除员工，返回是否成功
     *
     * @param id
     * @return
     */
    Boolean deleteById(@Param("id") Integer id);

    /**
     * 获取员工id集合
     *
     * @return
     * @author colg
     */
    List<String> selectIds();

    /**
     * 根据员工id集合查询名称集合
     *
     * @param ids
     * @return
     * @author colg
     */
    List<String> selectLastNamesByIds(@Param("ids") List<String> ids);
    
    /**
     * 根据姓名模糊查询员工列表
     *
     * @param lastName
     * @return
     * @author colg
     */
    List<Dict> queryByLastNameResultDict(@Param("lastName") String lastName);
    
}
