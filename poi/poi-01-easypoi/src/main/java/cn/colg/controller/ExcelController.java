package cn.colg.controller;

import static cn.colg.util.ResultVoUtil.success;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.colg.util.ImageUtil;
import cn.colg.vo.ResultVo;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author colg
 */
@CrossOrigin
@Slf4j
@RestController
public class ExcelController {

    @GetMapping("/excel")
    public ResultVo excel() {
        Dict dict = Dict.create().set("hello", "excel");
        return success(dict);

    }

    /**
     * 上传图片
     *
     * @param multipartFile
     * @return
     * @throws IOException
     * @author colg
     */
    @PostMapping("/uploadImage")
    public ResultVo uploadImage(@RequestParam(value = "image-1", required = false) MultipartFile multipartFile,
                                @RequestParam String username,
                                @RequestParam String password) throws IOException {
        Console.log(username);
        String originalFilename = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        log.info("ExcelController.uploadImage() >> 上传图片 : name={}, type={}", originalFilename, contentType);

        String img = ImageUtil.saveImg(multipartFile, originalFilename);
        return success(img);
    }

    @PostMapping("/importStudent")
    public ResultVo importStudent(@RequestParam(value = "student-01", required = false) MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        log.info("ExcelController.uploadImage() >> 导入: name={}, type={}", originalFilename, contentType);
        
        FileInputStream input = (FileInputStream)multipartFile.getInputStream();
        ImportParams params = new ImportParams();
        List<Map<String, Object>> list = ExcelImportUtil.importExcel(input, Map.class, params);
        Console.log(list.size());
        list.forEach(e -> Console.log(JSON.toJSONString(e)));
        return success(list);
    }

}
