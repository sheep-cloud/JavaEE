package cn.colg.bean;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

/**
 * 员工实体
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Table(name = "tabple_emp")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer empId;
    private String empName;
    private Double empSalary;
    private Integer empAge;
}
