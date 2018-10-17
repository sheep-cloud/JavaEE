package cn.colg.rbac.controller;

import static cn.colg.rbac.util.ResultVoUtil.success;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.colg.rbac.core.BaseController;
import cn.colg.rbac.dto.PermissionDto;
import cn.colg.rbac.entity.Permission;
import cn.colg.rbac.vo.ResultVo;

/**
 * @author colg
 */
@CrossOrigin
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    /**
     * 回显菜单树形结构（角色已拥有的勾选）
     *
     * @param roleId
     * @return
     */
    @PostMapping("/loadAssignData")
    @ResponseBody
    public ResultVo loadAssignData(@RequestParam String roleId) {
        List<PermissionDto> pds = permissionService.querAll();
        // 获取当前角色已经分配的许可信息
        List<String> permissionIds = permissionService.queryPermissionIdsByRoleId(roleId);
        // 把数据装到map里，使用map的索引
        Map<String, PermissionDto> permissionDtoMap = new HashMap<>(16);
        for (PermissionDto pd : pds) {
            if (permissionIds.contains(pd.getId())) {
                pd.setChecked(true);
            } else {
                pd.setChecked(false);
            }
            permissionDtoMap.put(pd.getId(), pd);
        }

        List<PermissionDto> permissionDtos = new ArrayList<>();
        // 遍历许可列表
        for (PermissionDto child : pds) {
            // 获取每一个节点的pid
            String pid = child.getPid();
            if ("0".equals(pid)) {
                // 如果为顶级节点，直接添加
                permissionDtos.add(child);
            } else {
                // 如果为子节点，则获取它的父节点，然后添加到父节点下面
                permissionDtoMap.get(pid).getChildren().add(child);
            }
        }

        return success(permissionDtos);
    }

    /**
     * 删除节点
     *
     * @param id
     * @return
     */
    @PostMapping("/delPermission")
    @ResponseBody
    public ResultVo delPermission(@RequestParam String id) {
        permissionService.delPermission(id);
        return success();
    }

    /**
     * 修改节点
     *
     * @param permission
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultVo update(Permission permission) {
        permissionService.update(permission);
        return success();
    }

    /**
     * 修改节点页面
     *
     * @return
     */
    @GetMapping("/edit")
    public String edit(String id, Model model) {
        Permission permission = permissionService.findById(id);
        model.addAttribute("permission", permission);
        return "permission/edit";
    }

    /**
     * 添加节点
     *
     * @param permission
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResultVo insert(Permission permission) {
        permissionService.insert(permission);
        return success();
    }

    /**
     * 添加节点页面
     *
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return "permission/add";
    }

    /**
     * 菜单树形结构
     *
     * @return
     */
    @PostMapping("/loadData")
    @ResponseBody
    public ResultVo loadData() {
        /// 模拟树形结构
        /*
        List<PermissionDto> permissionDtos = new ArrayList<>();
        PermissionDto root = new PermissionDto();
        root.setName("父节点1 - 展开11111111111");
        PermissionDto children = new PermissionDto();
        children.setName("父节点11 - 折叠");
        root.getChildren().add(children);
        permissionDtos.add(root);
        */

        /// 一级一级查询，只能查询到固定子级，添加子级，需要修改代码
        /*
        List<PermissionDto> permissionDtos = new ArrayList<>();
        // 获取顶级结构
        PermissionDto root = permissionService.queryRootPermission();
        // 获取二级结构
        List<PermissionDto> childerns = permissionService.queryChildPermission(root.getId());
        for (PermissionDto childern : childerns) {
            List<PermissionDto> childernChilderns = permissionService.queryChildPermission(childern.getId());
            childern.setChildren(childernChilderns);
        }
        root.setChildren(childerns);
        permissionDtos.add(root);
        */

        /// 递归方式读取许可数据，效率不高，要查询很多次数据库
        /*
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId("0");
        queryChildPermissions(permissionDto);
        return success(permissionDto.getChildren());
        */

        /// 嵌套for循环方式读取许可数据，此方法只查询一次数据库，但是遍历时，嵌套循环，并且没有用到索引，会影响性能
        /*
        // 查询所有的许可数据
        List<PermissionDto> permissionDtos = new ArrayList<>();
        // 查询所有许可
        List<PermissionDto> pds = permissionService.querAll();
        // 遍历许可列表
        for (PermissionDto pd : pds) {
            // 子节点
            if ("0".equals(pd.getPid())) {
                // 如果是 顶级节点，直接添加到集合中
                permissionDtos.add(pd);
            } else {
                // 遍历所有许可，用于和子节点做对比
                for (PermissionDto innerPermission : pds) {
                    // 如果子节点和其中的父节点相同
                    if (pd.getPid().equals(innerPermission.getId())) {
                        // 组合父子节点的关系
                        innerPermission.getChildren().add(pd);
                        break;
                    }
                }
            }
        }
        */

        /// map集合方式读取许可数据（推荐）
        List<PermissionDto> pds = permissionService.querAll();
        // 把数据装到map里，使用map的索引
        Map<String, PermissionDto> permissionDtoMap = new HashMap<>(16);
        pds.forEach(pd -> permissionDtoMap.put(pd.getId(), pd));

        List<PermissionDto> permissionDtos = new ArrayList<>();
        // 遍历许可列表
        pds.forEach(child -> {
            // 获取每一个节点的pid
            String pid = child.getPid();
            String zero = "0";
            if (zero.equals(pid)) {
                // 如果为顶级节点，直接添加
                permissionDtos.add(child);
            } else {
                // 如果为子节点，则获取它的父节点，然后添加到父节点下面
                permissionDtoMap.get(pid).getChildren().add(child);
            }
        });

        return success(permissionDtos);
    }

    /**
     * 递归查询许可信息 1. 方法自己调用自己 2. 方法一定要存在跳出逻辑 3. 方法调用时候，参数之间应该有规律 4. 递归算法，效率比较低
     *
     * @param permissionDto
     */
    protected void queryChildPermissions(PermissionDto permissionDto) {
        List<PermissionDto> childPermission = permissionService.queryChildPermission(permissionDto.getId());

        // 递归查询，当没有子节点时跳出
        for (PermissionDto dto : childPermission) {
            queryChildPermissions(dto);
        }

        permissionDto.setChildren(childPermission);
    }

    /**
     * 权限菜单列表页面
     *
     * @return
     */
    @GetMapping("/tree")
    public String tree() {
        return "permission/tree";
    }
}
