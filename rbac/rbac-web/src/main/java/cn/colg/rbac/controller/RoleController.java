package cn.colg.rbac.controller;

import static cn.colg.rbac.util.ResultVoUtil.success;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.colg.rbac.core.BaseController;
import cn.colg.rbac.entity.Role;
import cn.colg.rbac.vo.ResultVo;

/**
 * @author colg
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    /**
     * 分配许可信息
     *
     * @return
     */
    @PostMapping("/doAssign")
    @ResponseBody
    public ResultVo doAssign(@RequestParam(value = "roleId") String roleId,
                             @RequestParam(value = "permissionIds") String[] permissionIds) {
        roleService.insertRolePermission(roleId, permissionIds);
        return success();
    }

    /**
     * 权限分配页面
     *
     * @return
     */
    @GetMapping("/assign")
    public String assign() {
        return "role/assign";
    }

    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    @PostMapping("/delRoles")
    @ResponseBody
    public ResultVo delRoles(@RequestParam(value = "id") String[] ids) {
        roleService.delRoles(ids);
        return success();
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @PostMapping("/delRole")
    @ResponseBody
    public ResultVo delRole(@RequestParam String id) {
        roleService.delRole(id);
        return success();
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultVo update(Role role) {
        roleService.updateRole(role);
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
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "role/edit";
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResultVo insert(Role role) {
        roleService.insertRole(role);
        return success();
    }

    /**
     * 新增页面
     *
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return "role/add";
    }

    /**
     * 异步分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param name 角色名称
     * @return
     */
    @PostMapping("/pageQuery")
    @ResponseBody
    public ResultVo pageQuery(Integer pageNum, Integer pageSize, String name) {
        return success(roleService.queryRole(pageNum, pageSize, name));
    }

    /**
     * 角色列表
     *
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return "role/list";
    }
}
