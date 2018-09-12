package cn.colg.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Excel 工具类
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ExcelExportSxssf {

    /** 定义工作表 */
    private SXSSFWorkbook workbook;
    /** 定义工作表中的sheet */
    private Sheet sheet;
    /** 定义单元格样式 */
    private CellStyle style;
    /** 定义保存在内存中的数量,-1表示手动控制 */
    private int flushRows;
    /** 导出文件行数 */
    private int rowNum;
    /** 导出文件列数 */
    private int colNum;
    /** 导出文件的存放路径，并且是虚拟目录指向的路径 */
    private String filePath;
    /** 下载导出文件的路径 */
    private String fileWebPath;
    /** 导出文件名 */
    private String fileName;
    /** 导出文件属性、标题 key:属性，value:标题名，通过反射获取对象属性值 */
    private LinkedHashMap<String, String> fieldMap;

    /**
     * 开始导出方法
     *
     * @param filePath 导出文件存放物理路径
     * @param fileWebPath 导出文件web下载路径
     * @param fileName 导出文件名的前缀
     * @param fieldMap 导出数据对象的字段名称，标题 key:属性，value:标题名
     * @param flushRows 写磁盘控制参数（保存在内存中的行数 -1 为不保存）
     * @return
     * @author colg
     */
    public static ExcelExportSxssf start(String filePath, String fileWebPath, String fileName, LinkedHashMap<String, String> fieldMap, int flushRows) {
        ExcelExportSxssf excelExportSXXSSF = new ExcelExportSxssf().setFilePath(filePath)
                                                                   .setFileWebPath(fileWebPath)
                                                                   .setFileName(fileName)
                                                                   .setFieldMap(fieldMap);
        // 创建workbook
        SXSSFWorkbook wb = new SXSSFWorkbook(flushRows);
        excelExportSXXSSF.setWorkbook(wb);
        // 创建sheet
        SXSSFSheet sheet = wb.createSheet();
        excelExportSXXSSF.setSheet(sheet);
        // 创建cellStyle
        CellStyle cellStyle = wb.createCellStyle();
        excelExportSXXSSF.setStyle(cellStyle);

        // 第一行冻结（标题）
        sheet.createFreezePane(0, 1, 0, 1);
        // 默认高度
        sheet.setDefaultRowHeightInPoints(20);
        // 默认宽度
        sheet.setDefaultColumnWidth(20);

        // 生成导出excel的标题
        excelExportSXXSSF.writeTitles();
        return excelExportSXXSSF;
    }

    /**
     * 设置导入文件的标题 开始生成导出excel的标题
     * 
     * @throws Exception
     */
    private void writeTitles() {
        List<String> fieldNames = new ArrayList<>(fieldMap.values());
        // 第0行，标题
        rowNum = 0;
        // 根据列标题得出列数
        colNum = fieldNames.size();
        Row row = sheet.createRow(rowNum);
        for (int cellNum = 0; cellNum < colNum; cellNum++) {
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(fieldNames.get(cellNum));
            // 标题单元格样式
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            cell.setCellStyle(style);
        }
    }

    /**
     * 向导出文件写数据
     * 
     * @param datalist 存放Object对象，仅支持单个自定义对象，不支持对象中嵌套自定义对象
     * @return
     */
    public <T> void writeDatasByObject(List<T> datalist) {
        List<String> fieldCodes = new ArrayList<>(fieldMap.keySet());
        for (int j = 0; j < datalist.size(); j++) {
         // 第一行，内容
            rowNum = rowNum + 1;
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < fieldCodes.size(); cellNum++) {
                T owner = datalist.get(j);
                // 通过反射获取属性的值
                Object value = invokeMethod(owner, fieldCodes.get(cellNum), new Object[] {});
                Cell cell = row.createCell(cellNum);
                String cellValue = "";
                if (value != null) {
                    // 日期时间格式化
                    switch (value.getClass().getSimpleName()) {
                        case "Date":
                            cellValue = DateUtil.formatDateTime((Date)value);
                            break;
                        default:
                            cellValue = value.toString();
                    }
                }

                cell.setCellValue(cellValue);
            }

        }

    }

    /**
     * 向导出文件写数据
     * 
     * @param datalist 存放字符串数组
     * @return
     */
    public void writeDatasByString(List<String> datalist) {
        rowNum = rowNum + 1;
        Row row = sheet.createRow(rowNum);
        int datalistSize = datalist.size();
        for (int cellnum = 0; cellnum < colNum; cellnum++) {
            Cell cell = row.createCell(cellnum);
            if (datalistSize > cellnum) {
                cell.setCellValue(datalist.get(cellnum));
            } else {
                cell.setCellValue("");
            }

        }
    }

    /**
     * 手动刷新方法,如果flushRows为-1则需要使用此方法手动刷新内存
     * 
     * @param flushRows
     * @throws Exception
     */
    public void flush(int flushNum) throws Exception {
        ((SXSSFSheet)sheet).flushRows(flushNum);
    }

    /**
     * 导出文件
     * 
     * @throws Exception
     */
    public String exportFile() {
        String filename = fileName + ".xlsx";
        FileOutputStream out = null;
        try {
            // 创建目录
            FileUtil.mkdir(filePath);
            out = new FileOutputStream(filePath + filename);
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(out);
        }
        return fileWebPath + filename;
    }
    
    /**
     * 反射方法，通过get方法获取对象属性
     * 
     * @param owner 对象
     * @param fieldName 属性名
     * @param args
     * @return
     * @throws Exception
     */
    private Object invokeMethod(Object owner, String fieldName, Object[] args) {

        Class<? extends Object> ownerClass = owner.getClass();
        String methodName = createGetter(fieldName);

        Class<?>[] argsClass = null;
        if (!ArrayUtils.isEmpty(args)) {
            int argsLength = args.length;
            argsClass = new Class[argsLength];
            for (int i = 0; i < argsLength; i++) {
                argsClass[i] = args[i].getClass();
            }
        }

        Object value = null;
        try {
            Method method = ownerClass.getMethod(methodName, argsClass);
            value = method.invoke(owner, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 通过属性名称拼凑getter方法
     * 
     * @param fieldName
     * @return
     */
    private String createGetter(String fieldName) {
        if (StringUtils.isBlank(fieldName)) {
            return null;
        }
        StringBuffer methodName = new StringBuffer("get");
        methodName.append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
        return methodName.toString();
    }

}
