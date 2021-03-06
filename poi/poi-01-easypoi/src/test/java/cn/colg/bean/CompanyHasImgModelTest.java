package cn.colg.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 公司测试 - 导出（集合）
 *
 * @author colg
 */
@Slf4j
public class CompanyHasImgModelTest extends BaseTest{

    /**
     * 导出
     *
     * @throws Exception
     * @author colg
     */
    @Test
    public void test01() throws Exception {
        List<CompanyHasImgModel> list = new ArrayList<>();
        int size = 10;
        for (int i = 0; i < size; i++) {
            CompanyHasImgModel companyHasImgModel = new CompanyHasImgModel();
            companyHasImgModel.setCompanyId(IdUtil.fastSimpleUUID())
                              .setCompanyName("百度-" + i)
                              .setCompanyLogo("E:\\upload\\file\\logo.png")
                              .setCompanyAddress("天河-" + i);
            list.add(companyHasImgModel);
        }
        
        ExportParams entity = new ExportParams("公司信息", "公司");
        entity.setHeight((short)64);
        Workbook workbook = ExcelExportUtil.exportExcel(entity, CompanyHasImgModel.class, list);
        
        String filePath = "E:\\upload\\file\\公司信息";
        String suffix = entity.getType().equals(ExcelType.HSSF) ? ".xls" : ".xlsx";
        File file = new File(filePath + suffix);
        FileUtil.mkParentDirs(file);
        OutputStream out = new FileOutputStream(file);
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
        params.setNeedSave(true);
        List<CompanyHasImgModel> list = ExcelImportUtil.importExcel(new File("E:\\upload\\file\\公司信息.xls"), CompanyHasImgModel.class, params );
        log.info("list.size(): {}", list.size());
        list.forEach(e -> log.info("e: {}", e));
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
        params.setNeedSave(true);
        List<Object> list = ExcelImportUtil.importExcel(new File("E:\\upload\\file\\公司信息.xls"), Map.class, params);
        log.info("list.size(): {}", list.size());
        list.forEach(e -> log.info("e: {}", JSON.toJSONString(e)));
    }
}
