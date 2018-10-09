package cn.colg.component;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import cn.hutool.core.lang.Dict;

/**
 * 给容器中加入自己定义的ErrorAttributes
 *
 * @author colg
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes{

    /**
     * 返回的map就是页面和json能获取的所有字段
     *
     * @param requestAttributes
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.put("company", "colg");
        
        // 异常处理器携带的数据
        // int SCOPE_REQUEST = 0;
        Dict ext = (Dict)requestAttributes.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        map.put("ext", ext);
        return map;
    }
}
