package cn.colg.tx.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private Integer age;

}
