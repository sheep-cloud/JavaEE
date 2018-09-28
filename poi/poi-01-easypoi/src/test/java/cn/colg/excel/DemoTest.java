package cn.colg.excel;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.colg.BaseTest;
import cn.hutool.core.lang.Console;

/**
 * 读取文件
 *
 * @author colg
 */
public class DemoTest extends BaseTest {

    @Test
    public void test01() throws Exception {
        File file = new File("E:\\upload\\file\\ypxx_2018-09-28.xlsx");
        List<Object> list = ExcelImportUtil.importExcel(file, Map.class, new ImportParams());
        Console.log(list.size());
        list.forEach(e -> Console.log(JSON.toJSONString(e)));
    }
}
