package cn.colg.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;

/**
 * 课程测试 - 导出
 *
 * @author colg
 */
public class CourseEntityTest {

    /**
     * 集合定义
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void test01() throws Exception {
        List<StudentEntity> studentlist1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StudentEntity studentEntity = new StudentEntity().setId(RandomUtil.simpleUUID())
                                                             .setName("Jack-" + i)
                                                             .setSex(i % 2 == 0 ? 1 : 2)
                                                             .setBirthday(DateUtil.offsetHour(new Date(), -i))
                                                             .setRegistrationDate(DateUtil.offsetMinute(new Date(), 1));
            studentlist1.add(studentEntity);
        }
        List<StudentEntity> studentlist2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StudentEntity studentEntity = new StudentEntity().setId(RandomUtil.simpleUUID())
                                                             .setName("Rose-" + i)
                                                             .setSex(i % 2 == 0 ? 1 : 2)
                                                             .setBirthday(DateUtil.offsetHour(new Date(), -i))
                                                             .setRegistrationDate(DateUtil.offsetMinute(new Date(), 1));
            studentlist2.add(studentEntity);
        }
        
        List<CourseEntity> list = new ArrayList<>();
        CourseEntity courseEntity1 = new CourseEntity(RandomUtil.simpleUUID(), "语文", studentlist1)
                                                    .setMathTeacher(new TeacherEntity(RandomUtil.simpleUUID(), "Jack"));
        CourseEntity courseEntity2 = new CourseEntity(RandomUtil.simpleUUID(), "数学", studentlist2)
                                                    .setMathTeacher(new TeacherEntity(RandomUtil.simpleUUID(), "Rose"));
        list.add(courseEntity1);
        list.add(courseEntity2);
        
        ExportParams entity = new ExportParams("各科学生", "课程");
        Workbook workbook = ExcelExportUtil.exportExcel(entity, CourseEntity.class, list);
        
        String file = "E:\\upload\\file\\课程";
        String suffix = entity.getType().equals(ExcelType.HSSF) ? ".xls" : ".xlsx";
        OutputStream out = new FileOutputStream(file + suffix);
        workbook.write(out);
        IoUtil.close(out);
    }

    /**
     * 导入
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void test02() throws Exception {
        // TODO colg [导入有bug]
        ImportParams params = new ImportParams();
        params.setTitleRows(2);
        params.setHeadRows(2);
        List<CourseEntity> list = ExcelImportUtil.importExcel(new File("E:\\upload\\file\\课程.xls"), CourseEntity.class, params);
        list.forEach(Console::log);
    }

    /**
     * Map导入
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void test03() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(2);
        List<Map<String, Object>> list = ExcelImportUtil.importExcel(new File("E:\\upload\\file\\课程.xls"), Map.class, params);
        list.forEach(Console::log);
    }
}
