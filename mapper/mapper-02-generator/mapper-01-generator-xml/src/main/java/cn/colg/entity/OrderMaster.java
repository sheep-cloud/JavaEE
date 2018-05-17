package cn.colg.entity;

import cn.colg.core.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * - @mbg.generated
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "order_master")
public class OrderMaster extends BaseEntity implements Serializable {
    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    private String orderId;

    /**
     * 买家名称
     */
    @Column(name = "buyer_name")
    private String buyerName;

    /**
     * 买家电话
     */
    @Column(name = "buyer_phone")
    private String buyerPhone;

    /**
     * 买家地址
     */
    @Column(name = "buyer_address")
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    @Column(name = "buyer_openid")
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    /**
     * 订单状态（默认0新下单）
     */
    @Column(name = "order_status")
    private Byte orderStatus;

    /**
     * 支付状态（默认0未支付）
     */
    @Column(name = "pay_status")
    private Byte payStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取买家名称
     *
     * @return buyer_name - 买家名称
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * 设置买家名称
     *
     * @param buyerName 买家名称
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    /**
     * 获取买家电话
     *
     * @return buyer_phone - 买家电话
     */
    public String getBuyerPhone() {
        return buyerPhone;
    }

    /**
     * 设置买家电话
     *
     * @param buyerPhone 买家电话
     */
    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    /**
     * 获取买家地址
     *
     * @return buyer_address - 买家地址
     */
    public String getBuyerAddress() {
        return buyerAddress;
    }

    /**
     * 设置买家地址
     *
     * @param buyerAddress 买家地址
     */
    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    /**
     * 获取买家微信openid
     *
     * @return buyer_openid - 买家微信openid
     */
    public String getBuyerOpenid() {
        return buyerOpenid;
    }

    /**
     * 设置买家微信openid
     *
     * @param buyerOpenid 买家微信openid
     */
    public void setBuyerOpenid(String buyerOpenid) {
        this.buyerOpenid = buyerOpenid;
    }

    /**
     * 获取订单总金额
     *
     * @return order_amount - 订单总金额
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * 设置订单总金额
     *
     * @param orderAmount 订单总金额
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * 获取订单状态（默认0新下单）
     *
     * @return order_status - 订单状态（默认0新下单）
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态（默认0新下单）
     *
     * @param orderStatus 订单状态（默认0新下单）
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取支付状态（默认0未支付）
     *
     * @return pay_status - 支付状态（默认0未支付）
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态（默认0未支付）
     *
     * @param payStatus 支付状态（默认0未支付）
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}