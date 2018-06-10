package cn.colg.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.colg.tx.bean.User;

/**
 * 
 *
 * @author colg
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User insert(User user) {
        String sql = "INSERT INTO tbl_user(id, username, age) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getAge());
        return user;
    }

}
