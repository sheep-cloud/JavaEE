package cn.colg.gmall.web;

import static cn.colg.util.ResultVoUtil.success;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.gmall.model.UserAddress;
import cn.colg.gmall.service.OrderService;
import cn.colg.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单Controller
 *
 * @author colg
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 初始化用户订单
     *
     * @param userId 用户id
     * @return
     * @author colg
     */
    @GetMapping("/initOrder")
    public ResultVo initOrder(String userId) {
        log.info("initOrder; userId: {}", userId);
        List<UserAddress> list = orderService.initOrder(userId);
        return success(list);
    }
}
