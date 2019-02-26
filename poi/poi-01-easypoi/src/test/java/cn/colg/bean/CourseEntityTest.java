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

import com.alibaba.fastjson.JSON;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 课程测试 - 导出
 *
 * @author colg
 */
@Slf4j
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
        int size = 10;
        for (int i = 0; i < size; i++) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(IdUtil.fastSimpleUUID())
                         .setName("Jack-" + i)
                         .setSex(i % 2 == 0 ? 1 : 2)
                         .setBirthday(DateUtil.offset(new Date(), DateField.YEAR, -i))
                         .setRegistrationDate(DateUtil.offsetMinute(new Date(), 1));
            studentlist1.add(studentEntity);
        }
        List<StudentEntity> studentlist2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(IdUtil.fastSimpleUUID())
                         .setName("Rose-" + i)
                         .setSex(i % 2 == 0 ? 1 : 2)
                         .setBirthday(DateUtil.offset(new Date(), DateField.YEAR, -i))
                         .setRegistrationDate(DateUtil.offsetMinute(new Date(), 1));
            studentlist2.add(studentEntity);
        }
        
        List<CourseEntity> list = new ArrayList<>();
        CourseEntity courseEntity1 = new CourseEntity(IdUtil.fastSimpleUUID(), "语文", studentlist1);
        courseEntity1.setMathTeacher(new TeacherEntity(IdUtil.fastSimpleUUID(), "Jack"));
                                                    
        CourseEntity courseEntity2 = new CourseEntity(IdUtil.fastSimpleUUID(), "数学", studentlist2);
        courseEntity2.setMathTeacher(new TeacherEntity(IdUtil.fastSimpleUUID(), "Rose"));
        list.add(courseEntity1);
        list.add(courseEntity2);
        
        ExportParams entity = new ExportParams("各科学生", "课程");
        entity.setHeight((short)10);
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
        // TODO: colg [导入有bug]
        ImportParams params = new ImportParams();
        params.setTitleRows(2);
        params.setHeadRows(2);
        List<CourseEntity> list = ExcelImportUtil.importExcel(new File("E:\\upload\\file\\课程.xls"), CourseEntity.class, params);
        log.info("list.size(): {}", list.size());
        list.forEach(e -> log.info("e: {}", e));
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
        log.info("list.size(): {}", list.size());
        list.forEach(e -> log.info("e: {}", JSON.toJSONString(e)));
    }
}
