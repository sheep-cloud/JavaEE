package cn.colg.core;

import java.util.Date;

/**
 * Entity的基类，建议所有实体类都继承
 *
 * @author colg
 */
public abstract class BaseEntity {

    /** 主键：UUID */
    private String id;

    /** 创建时间："YYYY-mm-dd HH:mm:ss" */
    private Date createDate;
    /** 修改时间："YYYY-mm-dd HH:mm:ss" */
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}