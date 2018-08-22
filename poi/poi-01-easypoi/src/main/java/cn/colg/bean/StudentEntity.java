package cn.colg.bean;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.colg.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 学生
 *
 * @author colg
 */
@Getter
@Setter
@Accessors(chain = true)
public class StudentEntity extends BaseEntity {

    /** id */
    private String id;

    /** 学生姓名 */
    @Excel(name = "学生姓名", height = 20, width = 30, isImportField = "true_st")
    private String name;

    /** 学生性别 */
    @Excel(name = "学生性别", replace = {"男_1", "女_2"}, suffix = "生", isImportField = "true_st")
    private int sex;

    /** 出生日期 */
    @Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true_st", width = 20)
    private Date birthday;

    /** 进校日期 */
    @Excel(name = "进校日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd")
    private Date registrationDate;

}
