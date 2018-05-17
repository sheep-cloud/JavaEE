package cn.colg.entity;

import cn.colg.core.BaseEntity;
import java.io.Serializable;
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
@Table(name = "product_category")
public class ProductCategory extends BaseEntity implements Serializable {
    /**
     * 商品类目id
     */
    @Id
    @Column(name = "category_id")
    private String categoryId;

    /**
     * 类目名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 类目编号（唯一））
     */
    @Column(name = "category_type")
    private Integer categoryType;

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
     * 获取商品类目id
     *
     * @return category_id - 商品类目id
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 设置商品类目id
     *
     * @param categoryId 商品类目id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取类目名称
     *
     * @return category_name - 类目名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置类目名称
     *
     * @param categoryName 类目名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取类目编号（唯一））
     *
     * @return category_type - 类目编号（唯一））
     */
    public Integer getCategoryType() {
        return categoryType;
    }

    /**
     * 设置类目编号（唯一））
     *
     * @param categoryType 类目编号（唯一））
     */
    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
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