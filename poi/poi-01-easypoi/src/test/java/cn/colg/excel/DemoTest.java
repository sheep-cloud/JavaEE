package cn.colg.excel;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.colg.BaseTest;
import lombok.extern.slf4j.Slf4j;

/**
 * 读取文件
 *
 * @author colg
 */
@Slf4j
public class DemoTest extends BaseTest {

    @Test
    public void test01() throws Exception {
        File file = new File("E:\\upload\\file\\ypxx_2018-09-28.xlsx");
        List<Object> list = ExcelImportUtil.importExcel(file, Map.class, new ImportParams());
        log.info("list.size(): {}", list.size());
        list.forEach(e -> log.info("e: {}", JSON.toJSONString(e)));
    }
    
}
