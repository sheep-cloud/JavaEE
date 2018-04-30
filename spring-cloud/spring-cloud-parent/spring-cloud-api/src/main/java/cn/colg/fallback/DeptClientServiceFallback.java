package cn.colg.fallback;

import static cn.colg.bean.ResultBean.fail;

import org.springframework.stereotype.Component;

import cn.colg.bean.ResultBean;
import cn.colg.entity.Dept;
import cn.colg.service.DeptClientService;
import feign.hystrix.FallbackFactory;

/**
 * 接口熔断机制处理 - 服务降级（必须设置'@Component'）
 *
 * @author colg
 */
@Component
public class DeptClientServiceFallback implements FallbackFactory<DeptClientService> {

    @Override
    public DeptClientService create(Throwable cause) {
        return new DeptClientService() {

            /** 通信异常 */
            private String msg = "Consumer客户端提供的降级信息，此刻服务Provider已经关闭！";

            @Override
            public ResultBean list() {
                return fail(msg);
            }

            @Override
            public ResultBean get(Long id) {
                return fail(msg);
            }

            @Override
            public ResultBean add(Dept dept) {
                return fail(msg);
            }
        };
    }

}
