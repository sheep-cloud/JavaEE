package cn.colg.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * User
 * 
 * <pre>
 * `@Entity`: 告诉JPA只是一个实体类（和数据表映射的类）
 * `@Table`: 指定和哪个数据表对应；如果省略默认表明就是user
 * </pre>
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键；自增 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 姓名 */
    @Column(name = "last_name", length = 64)
    private String lastName;

    /** 邮箱 */
    @Column(name = "email", length = 64)
    private String email;

}
