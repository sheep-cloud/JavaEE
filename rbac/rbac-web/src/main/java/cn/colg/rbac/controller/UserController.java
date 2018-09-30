package cn.colg.rbac.controller;

import static cn.colg.rbac.util.ResultVoUtil.success;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.colg.rbac.core.BaseController;
import cn.colg.rbac.entity.Role;
import cn.colg.rbac.entity.User;
import cn.colg.rbac.vo.ResultVo;

/**
 * 用户管理
 *
 * @author colg
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 取消分配角色数据
     *
     * @return
     */
    @PostMapping("/dounAssign")
    @ResponseBody
    public ResultVo dounAssign(String userId, String[] assignRoleIds) {
        // 删除关系表数据
        userService.deleteUserRoles(userId, assignRoleIds);
        return success();
    }

    /**
     * 分配角色数据
     *
     * @return
     */
    @PostMapping("/doAssign")
    @ResponseBody
    public ResultVo doAssign(String userId, String[] unAssignRoleIds) {
        // 增加关系表数据
        userService.insertUserRoles(userId, unAssignRoleIds);
        return success();
    }

    /**
     * 分配角色页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/assign")
    public String assign(String id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);

        // 已经分配的角色和未分配的角色
        List<Role> assignRoles = new ArrayList<>();
        List<Role> unAssignRoles = new ArrayList<>();

        // 获取关系表的数据
        List<String> roleIds = userService.selectRoleIdsByUserId(id);
        // 查询所有角色
        List<Role> roleList = roleService.selectAll();

        roleList.forEach(role -> {
            if (roleIds.contains(role.getId())) {
                // 分配过
                assignRoles.add(role);
            } else {
                // 没有分配过
                unAssignRoles.add(role);
            }
        });

        model.addAttribute("assignRoles", assignRoles);
        model.addAttribute("unAssignRoles", unAssignRoles);
        return "user/assignRole";
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @PostMapping("/delUsers")
    @ResponseBody
    public ResultVo delUsers(@RequestParam(value = "id") String[] ids) {
        userService.delUsers(ids);
        return success();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @PostMapping("/delUser")
    @ResponseBody
    public ResultVo delUser(@RequestParam String id) {
        userService.delUser(id);
        return success();
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultVo update(User user) {
        userService.updateUser(user);
        return success();
    }

    /**
     * 编辑页面
     *
     * @param id
     * @return
     */
    @GetMapping("/edit")
    public String edit(@RequestParam String id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResultVo insert(User user) {
        userService.insertUser(user);
        return success();
    }

    /**
     * 新增页面
     *
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return "user/add";
    }

    /**
     * 异步分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param loginacct 账号
     * @return
     */
    @PostMapping("/pageQuery")
    @ResponseBody
    public ResultVo pageQuery(Integer pageNum, Integer pageSize, String loginacct) {
        return success(userService.queryUser(pageNum, pageSize, loginacct));
    }

    /**
     * 用户列表
     *
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return "user/list";
    }
}
