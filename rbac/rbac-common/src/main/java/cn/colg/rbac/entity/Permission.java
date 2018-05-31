package cn.colg.rbac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.colg.rbac.core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * - @mbg.generated
 *
 * @author colg
 */
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "permission")
public class Permission extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * url
     */
    @Column(name = "url")
    private String url;

    /**
     * 上级id
     */
    @Column(name = "pid")
    private String pid;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取url
     *
     * @return url - url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置url
     *
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取上级id
     *
     * @return pid - 上级id
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置上级id
     *
     * @param pid 上级id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}