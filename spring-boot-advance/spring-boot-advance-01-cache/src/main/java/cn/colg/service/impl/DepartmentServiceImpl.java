package cn.colg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import cn.colg.bean.Department;
import cn.colg.mapper.DepartmentMapper;
import cn.colg.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;

/**
 * DepartmentServiceImpl
 *
 * @author colg
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Qualifier("deptCacheManager")
    @Autowired
    private RedisCacheManager deptCacheManager;

    @Override
    public List<Department> query() {
        return departmentMapper.query();
    }

    /**
     * 根据id查询部门信息; (注解方式)
     * 
     * <pre>
     * 缓存的数据能存入redis;
     * 第二次从缓存中查询不能反序列化回来;
     * 存的是dept的json数据集; cacheManager默认使用RedisTemplate<Object, Employee>操作redis
     * </pre>
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"dept"}, cacheManager = "deptCacheManager")
    @Override
    public Department getDeptById(Integer id) {
        log.info("查询的部门ID： {}", id);
        return departmentMapper.getDeptById(id);
    }

    @Override
    public void insertDept(Department department) {
        departmentMapper.insertDept(department);
    }

    /**
     * 根据id查询部门信息; (编码方式)
     *
     * @param id
     * @return
     */
    @Override
    public Department getDept2ById(Integer id) {
        log.info("查询的部门ID： {}", id);

        // 查询缓存
        Cache cache = deptCacheManager.getCache("dept");
        Department department = cache.get(id, Department.class);
        if (department != null) {
            return department;
        }

        // 查询数据库
        department = departmentMapper.getDeptById(id);
        cache.put(id, department);
        return department;
    }

}
