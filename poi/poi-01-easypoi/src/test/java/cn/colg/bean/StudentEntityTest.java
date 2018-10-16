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
import cn.colg.BaseTest;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;

/**
 * 学生测试 - 基本
 *
 * @author colg
 */
public class StudentEntityTest extends BaseTest {

    /**
     * 对象定义 - 导出
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void test01() throws Exception {
        List<StudentEntity> list = new ArrayList<>();
        for (int i = 0, size = 100; i < size; i++) {
            StudentEntity studentEntity = new StudentEntity().setId(IdUtil.simpleUUID())
                                                             .setName("Jack-" + i)
                                                             .setSex(i % 2 == 0 ? 1 : 2)
                                                             .setBirthday(DateUtil.offset(new Date(), DateField.YEAR, -i))
                                                             .setRegistrationDate(DateUtil.offsetMinute(new Date(), i));
            list.add(studentEntity);
        }
        
        ExportParams entity = new ExportParams("计算机一班学生", "学生");
        entity.setHeight((short)20);
        Workbook workbook = ExcelExportUtil.exportExcel(entity, StudentEntity.class, list);
        
        String filePath = "E:\\upload\\file\\计算机";
        String suffix = entity.getType().equals(ExcelType.HSSF) ? ".xls" : ".xlsx";
        OutputStream out = new FileOutputStream(filePath + suffix);
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
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        File file = new File("E:\\upload\\file\\计算机.xls");
        List<StudentEntity> list = ExcelImportUtil.importExcel(file, StudentEntity.class, params);
        Console.log(list.size());
        list.forEach(Console::log);
    }
    
    /**
     * Map 导入
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void test03() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        File file = new File("E:\\upload\\file\\计算机.xls");
        List<Map<String, Object>> list = ExcelImportUtil.importExcel(file, Map.class, params);
        Console.log(list.size());
        list.forEach(e -> Console.log(JSON.toJSONString(e)));
    }

}
