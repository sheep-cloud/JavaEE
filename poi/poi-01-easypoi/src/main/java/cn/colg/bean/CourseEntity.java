package cn.colg.bean;

import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 课程
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CourseEntity extends BaseEntity {

    /** 主键 */
    private String id;

    /** 课程名称 */
    @Excel(name = "课程名称", orderNum = "1", width = 25, needMerge = true)
    private String name;

    /** 老师主键 */
    @ExcelEntity(id = "major")
    private TeacherEntity mathTeacher;

    /** 学生集合 */
    @ExcelCollection(name = "学生", orderNum = "4")
    private List<StudentEntity> students;

    /// ----------------------------------------------------------------------------------------------------

    public CourseEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseEntity(String id, String name, List<StudentEntity> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

}
