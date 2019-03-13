package cn.colg.rbac.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.colg.rbac.dto.PermissionDto;
import cn.colg.rbac.service.PermissionService;
import cn.hutool.core.util.StrUtil;

/**
 * 权限拦截器
 *
 * @author colg
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取用户的请求地址
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        String path = session.getServletContext().getContextPath();

        // 查询所有需要验证的路径集合
        List<PermissionDto> permissionDtoList = permissionService.querAll();
        Set<String> uriSet = new HashSet<>();
        permissionDtoList.forEach(permissionDto -> {
            String permissionDtoUrl = permissionDto.getUrl();
            if (StrUtil.isNotBlank(permissionDtoUrl)) {
                uriSet.add(path + permissionDtoUrl);
            }
        });

        // 判断当前路径是否需要进行权限验证
        if (uriSet.contains(uri)) {
            // 权限验证
            // 判断当前用户是否拥有对应的权限
            @SuppressWarnings("unchecked")
            Set<String> authUriSet = (Set<String>)session.getAttribute("authUriSet");
            if (authUriSet.contains(uri)) {
                return true;
            } else {
                // 重定向到错误页面
                response.sendRedirect(path + "/error");
                return false;
            }
        } else {
            return true;
        }
    }
}
