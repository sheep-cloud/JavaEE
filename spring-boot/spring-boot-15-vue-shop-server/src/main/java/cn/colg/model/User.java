package cn.colg.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.alibaba.fastjson.annotation.JSONField;

import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户实体
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Document(collection = "users")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户名 */
    private String name;
    /** 密码 */
    private String pwd;
    /** 手机号码 */
    private String phone;

    /** Mongo 主键 */
    @JSONField(name = "_id")
    @Id
    private String _id;

}
