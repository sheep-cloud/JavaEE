package cn.rule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 负载均衡策略，修改为自定义规则
 * 
 * <pre>
 * 轮询，每台机器访问5次
 * </pre>
 *
 * @author colg
 */
public class MyRundomRule extends AbstractLoadBalancerRule {

    /*
     * total = 0;           当total==5以后，指针往下走
     * currentIndex = 0;    当前对外提供服务的服务器地址
     * total需要重新置为零，但是已经达到过一个5次，index = 1
     * 
     * 分析：
     *  微服务只有8001，8002，8003，三次。索引=2后，从0开始
     */

    /// ----------------------------------------------------------------------------------------------------

    /** 总共被调用的次数，目前要求每台被调用5次 */
    private int total = 0;
    /** 当前提供服务的机器索引 */
    private int currentIndex = 0;

    /**
     * 负载均衡策略规则：轮询，每台机器5次
     *
     * @param lb
     * @param key
     * @return
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                // 如果当前线程中断
                return null;
            }
            // 获取已启动，可到达的服务器
            List<Server> upList = lb.getReachableServers();
            // 获取所有已知的服务器
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                // 没有服务器
                return null;
            }

            if (total < 5) {
                // 如果total<5，从索引0里获取服务器
                server = upList.get(currentIndex);
                total++;
            } else {
                total = 0;
                currentIndex++;
                if (currentIndex >= upList.size()) {
                    // 如果当前索引，超过已启动的服务器，则从0开始
                    currentIndex = 0;
                }
            }

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return server;
            }

            server = null;
            Thread.yield();
        }

        return server;
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {}

}
