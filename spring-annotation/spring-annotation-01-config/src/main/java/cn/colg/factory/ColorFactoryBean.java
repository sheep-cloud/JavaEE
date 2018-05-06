package cn.colg.factory;

import org.springframework.beans.factory.FactoryBean;

import cn.colg.bean.Color;
import lombok.extern.slf4j.Slf4j;

/**
 * 创建一个Spring定义的FactoryBean
 *
 * @author colg
 */
@Slf4j
public class ColorFactoryBean implements FactoryBean<Color> {

    /**
     * 返回由该工厂管理的对象的实例（可能共享或独立）。
     * 
     * 与BeanFactory一样，这允许支持Singleton和Prototype设计模式。
     * 
     * 返回一个Color对象，这个对象会添加到容器中。
     *
     * @return
     * @throws Exception
     */
    @Override
    public Color getObject() throws Exception {
        log.info("getObject() >>  : {}", "ColorFactoryBean....");
        return new Color();
    }

    /**
     * 返回对象类型
     *
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 是否单例？
     * 
     * true：这个bean是单实例，在容器中保存一份
     * 
     * false：多实例，每次获取都会创建一个新的Bean
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

}
