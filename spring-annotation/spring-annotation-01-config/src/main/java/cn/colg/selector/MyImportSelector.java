package cn.colg.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑返回需要导入的组件
 *
 * @author colg
 */
public class MyImportSelector implements ImportSelector {

    /**
     * 根据导入@Configuration类的AnnotationMetadata选择并返回应该导入哪个类的名称。
     * 
     * 返回值就是导入到容器中的组件全类名
     *
     * @param importingClassMetadata 当前标注@Import注解的类的所有信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 返回不要返回null值，会包npe
        return new String[] {
                "cn.colg.bean.Bule",
                "cn.colg.bean.Yellow"
            };
    }

}
