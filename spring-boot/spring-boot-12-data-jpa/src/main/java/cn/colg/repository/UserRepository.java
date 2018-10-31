package cn.colg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.colg.bean.User;

/**
 * UserRepository
 * 
 * <pre>
 * 继承JpaRepository<T, ID extends Serializable>来完成对数据库的操作
 * </pre>
 *
 * @author colg
 */
public interface UserRepository extends JpaRepository<User, Integer>{

}
