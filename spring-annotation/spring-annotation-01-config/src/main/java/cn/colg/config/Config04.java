package cn.colg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cn.colg.selector.MyImportSelector;

/**
 * 配置类；'@Import'
 * 
 * <pre>
 * '@Import'：  快速导入组件
 *  1. '@Import(value = {Color.class, Red.class})'： 容器中就会自动注册这个组件，id默认是全类名
 *  2. '@Import(value = {MyImportSelector.class})'： 返回需要导入的组件的全类名数组
 * </pre>
 * 
 * @author colg
 */
@Import(value = {MyImportSelector.class})
@Configuration
public class Config04 {

}
