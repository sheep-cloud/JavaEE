package cn.colg.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import cn.hutool.core.util.ArrayUtil;

/**
 * 自定义过滤器 - 需要导入 javax.servlet-api
 *
 * @author colg
 */
public class RolesOrFilter extends AuthorizationFilter {

    /**
     * 自定义过滤器，有其中一个角色就返回true
     *
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String[] roles = (String[])mappedValue;
        // 如果 角色为空，返回true
        if (ArrayUtil.isEmpty(roles)) {
            return true;
        }
        
        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }

}
