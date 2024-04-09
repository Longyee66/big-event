package com.bigevent.controller;

import com.bigevent.pojo.Result;
import com.bigevent.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传接口
 */
@RestController
@RequestMapping("/admin/upload")
@Api(tags = "其他接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    @PostMapping
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
        //避免文件覆盖
        try {
            //原始文件名
            String originalFileName=file.getOriginalFilename();
            //截取文件后缀
            String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
            //构造新的文件名
            String objectName= UUID.randomUUID().toString()+extension;

            //文件的请求路径
            String filePath=aliOssUtil.upload(file.getBytes(),objectName);
            return Result.success(filePath);
        }catch (IOException e){
            log.error("文件上传失败：{}",e);
        }
        return Result.error("文件上传失败");
    }
}
