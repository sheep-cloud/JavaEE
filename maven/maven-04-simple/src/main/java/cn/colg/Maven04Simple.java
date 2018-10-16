package cn.colg;

/**
 * Maven04Simple
 *
 * @author colg
 */
public class Maven04Simple {

    /**
     * 调用 maven-03-simple 的 sayHello 方法
     *
     * @param name
     * @return
     * @author colg
     */
    public String sayHello(String name) {
        Maven03Simple maven03Simple = new Maven03Simple();
        return maven03Simple.sayHello(name);
    }
}
