package cn.colg.controller;

import static cn.colg.util.ResultVoUtil.success;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.bean.User;
import cn.colg.repository.UserRepository;
import cn.colg.vo.ResultVo;

/**
 * UserController
 *
 * @author colg
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/query")
    public ResultVo query() {
        List<User> list = userRepository.findAll();
        return success(list);
    }

    @GetMapping("/user/{id}")
    public ResultVo getUser(@PathVariable("id") Integer id) {
        User user = userRepository.findOne(id);
        return success(user);
    }

    @GetMapping("/user")
    public ResultVo insertUser(User user) {
        User dbUser = userRepository.save(user);
        return success(dbUser);
    }
}
