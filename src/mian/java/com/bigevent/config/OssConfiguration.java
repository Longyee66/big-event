package com.bigevent.config;


import com.bigevent.pojo.AliOssProperties;
import com.bigevent.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用来创建AliOssUtil对象
 */
@Configuration
@Slf4j
public class OssConfiguration {
    @Bean //将这个方法加入bean管理
    @ConditionalOnMissingBean //该工具类对象只用创建一次 条件对象
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("AliOssUtil文件上传工具类创建");
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
}
