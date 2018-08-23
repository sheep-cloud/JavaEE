package cn.colg.excel;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

import cn.colg.BaseTest;
import cn.colg.bean.Ypxx;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.NumberUtil;

/**
 * poi测试
 *
 * @author colg
 */
public class ExcelExportSXSSFTest extends BaseTest{
    
    @Test
    public void testExportFile() throws Exception {
        // 导出文件存放的路径
        String filePath = "E:/upload/file\\";
        // 导出文件的前缀
        String fileName = "ypxx_" + DateUtil.today();
        // -1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
        int flushRows = 100;

        // 要导出数据的对象中的属性，标题，让ExcelExportSXXSSF通过反射获取对象的值
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>(3);
        fieldMap.put("bm", "流水号");
        fieldMap.put("mc", "通用名");
        fieldMap.put("price", "价格");
        fieldMap.put("ypjybgyxq", "药品检验报告有效期");

        // 开始导出，执行一些workbook及sheet等对象的初始创建
        ExcelExportSXSSF excelExportSXXSSF = ExcelExportSXSSF.start(filePath, "http://127.0.0.1/file/", fileName, fieldMap, flushRows);

        // 准备导出的数据，将数据存入list，且list中对象的字段名称必须是刚才传入ExcelExportSXXSSF的名称
        List<Ypxx> list = new ArrayList<Ypxx>();
        int size = 1000;
        for (int i = 0; i < size; i++) {
            Ypxx ypxx = new Ypxx().setBm(NumberUtil.decimalFormat("0000", i))
                                  .setMc("青霉素-" + i)
                                  .setPrice(NumberUtil.roundStr(2.5f + i, 2))
                                  .setYpjybgyxq(DateUtil.offsetHour(new Date(), i));
            list.add(ypxx);
        }
        // 执行导出
        excelExportSXXSSF.writeDatasByObject(list);
        // 输出文件，返回下载文件的http地址
        String webpath = excelExportSXXSSF.exportFile();

        Console.log(webpath);
    }
    
    @Test
    public void testName() throws Exception {
        Console.log(NumberUtil.decimalFormat("#.00", 252.5));
    }
    
}
