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
@Table(name = "product_info")
public class ProductInfo extends BaseEntity implements Serializable {
    /**
     * 商品id
     */
    @Id
    @Column(name = "product_id")
    private String productId;

    /**
     * 商品类目编号
     */
    @Column(name = "category_type")
    private Integer categoryType;

    /**
     * 名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 单价
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 库存
     */
    @Column(name = "product_stock")
    private Integer productStock;

    /**
     * 描述
     */
    @Column(name = "product_description")
    private String productDescription;

    /**
     * 小图
     */
    @Column(name = "product_icon")
    private String productIcon;

    /**
     * 状态（0正常，1下架；默认0）
     */
    @Column(name = "product_status")
    private Byte productStatus;

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
     * 获取商品id
     *
     * @return product_id - 商品id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置商品id
     *
     * @param productId 商品id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取商品类目编号
     *
     * @return category_type - 商品类目编号
     */
    public Integer getCategoryType() {
        return categoryType;
    }

    /**
     * 设置商品类目编号
     *
     * @param categoryType 商品类目编号
     */
    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    /**
     * 获取名称
     *
     * @return product_name - 名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置名称
     *
     * @param productName 名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取单价
     *
     * @return product_price - 单价
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * 设置单价
     *
     * @param productPrice 单价
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 获取库存
     *
     * @return product_stock - 库存
     */
    public Integer getProductStock() {
        return productStock;
    }

    /**
     * 设置库存
     *
     * @param productStock 库存
     */
    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    /**
     * 获取描述
     *
     * @return product_description - 描述
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * 设置描述
     *
     * @param productDescription 描述
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * 获取小图
     *
     * @return product_icon - 小图
     */
    public String getProductIcon() {
        return productIcon;
    }

    /**
     * 设置小图
     *
     * @param productIcon 小图
     */
    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    /**
     * 获取状态（0正常，1下架；默认0）
     *
     * @return product_status - 状态（0正常，1下架；默认0）
     */
    public Byte getProductStatus() {
        return productStatus;
    }

    /**
     * 设置状态（0正常，1下架；默认0）
     *
     * @param productStatus 状态（0正常，1下架；默认0）
     */
    public void setProductStatus(Byte productStatus) {
        this.productStatus = productStatus;
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