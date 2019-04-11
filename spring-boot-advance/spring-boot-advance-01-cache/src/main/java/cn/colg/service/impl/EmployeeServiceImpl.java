package cn.colg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import cn.colg.bean.Employee;
import cn.colg.mapper.EmployeeMapper;
import cn.colg.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

/**
 * EmployeeServiceImpl
 * 
 * <pre>
 * `@CacheConfig`: 抽取缓存的公共配置
 * </pre>
 *
 * @author colg
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Qualifier("empCacheManager")
    @Autowired
    private RedisCacheManager empCacheManager;

    @Override
    public List<Employee> query() {
        return employeeMapper.query();
    }

    /**
     * 根据id查询员工信息; (注解方式)
     * 
     * <pre>
     * `@Cacheable`: 将方法的运行结果进行缓存, 以后再要相同的数据, 直接从缓存中获取, 不用调用方法.
     *  CacheManager管理多个Cache组件的, 对缓存的真正CRUD操作在Cache组件中, 每一个
     *      几个属性
     *          cacheNames/value:   指定缓存的名称, 必须指定至少一个
     *          key:                缓存数据使用的key, 可以用它来指定. 默认是使用方法参数的值
     *                              编写SpEL:  `#id`: 参数id的值
     *          keyGenerator:       key的生成器; 可以自己指定key的生成器的组件id
     *                              key/keyGenerator: 二选一
     *          condition:          指定符合条件的情况下缓存;
     *          unless:             否定缓存; 当unless指定的条件为true, 方法的返回值就不会被缓存, 可以获取到结果进行判断
     *          sync:               是否使用异步模式
     *          
     * 原理:
     *  1. 自动配置类
     *  2. 缓存的配置类
     *  3. 哪个配置类默认生效: SimpleCacheConfiguration
     *  4. 给容器中注册了一个 CacheManager:  ConcurrentMapCacheManager
     *  5. 可以获取和创建ConcurrentMapCacheManager类型的缓存组件; 他的作用将数据保存在 ConcurrentMap中;
     *  
     * 运行流程：
     *  `@Cacheable`:
     *      1. 方法运行之前, 先去查询Cache(缓存组件), 按照cacheNames指定的名字获取;
     *          (CacheManager先获取相应的缓存), 第一次获取缓存如果没有会自动创建
     * </pre>
     *
     * @param id
     * @return
     * @author colg
     */
    @Cacheable(cacheNames = {"emp"})
    @Override
    public Employee getEmpById(Integer id) {
        log.info("查询的员工id: {}", id);
        return employeeMapper.getEmpById(id);
    }

    @Override
    public void insertEmp(Employee employee) {
        employeeMapper.insertEmp(employee);
    }

    /**
     * 根据id查询员工信息; (编码方式)
     *
     * @param id
     * @return
     */
    @Override
    public Employee getEmp2ById(Integer id) {
        log.info("查询的员工id: {}", id);

        // 查询缓存
        Cache cache = empCacheManager.getCache("emp");
        Employee employee = cache.get(id, Employee.class);
        if (employee != null) {
            return employee;
        }

        // 缓存没有, 查询数据库
        employee = employeeMapper.getEmpById(id);
        // 把结果添加到缓存
        cache.put(id, employee);
        return employee;
    }

}
