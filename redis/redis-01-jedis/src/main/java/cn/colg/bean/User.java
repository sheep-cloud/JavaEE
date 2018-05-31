package cn.colg.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户表
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String password;
    private Date createTime;

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\",\"username\":\"" + username + "\",\"password\":\"" + password + "\",\"createTime\":\"" + createTime + "\"}";
    }

}
