package cn.colg.bean;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import cn.colg.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Github 用户
 *
 * @author colg
 */
@Getter
@Setter
@Accessors(chain = true)
public class Users extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer totalCount;
    private Boolean incompleteResults;
    @JSONField(ordinal = 1)
    private List<Items> items;

    public Integer getTotalCount() {
        this.totalCount = this.items.size();
        return totalCount;
    }
}
