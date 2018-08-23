package cn.colg.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;

/**
 * 图片处理
 *
 * @author colg
 */
public class ImageUtil {

    public static String saveImg(MultipartFile multipartFile, String originalFilename) throws IOException {
        File rootPath = FileUtil.mkdir("E:\\rams\\bu_device_data");
        String fileName = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN) + "." + FileUtil.extName(originalFilename);
        String filePath = rootPath + File.separator + fileName;
        multipartFile.transferTo(new File(filePath));
        return "http://127.0.0.1:8080/" + fileName;
    }
}
