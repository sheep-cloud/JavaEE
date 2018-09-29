package cn.colg.component;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

import cn.hutool.core.util.StrUtil;

/**
 * 区域解析器，可以在链接上携带区域信息
 *
 * @author colg
 */
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 通过给定的请求解析当前的语言环境
     *
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getParameter("language");
        Locale locale = Locale.getDefault();
        if (StrUtil.isNotEmpty(language)) {
            // 获取 语言_国家
            String[] split = language.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {}

}
