package com.thinking.common.file.service;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.thinking.common.core.constants.CacheConstants;
import com.thinking.common.core.constants.Constants;
import com.thinking.common.core.enums.ResultCode;
import com.thinking.common.core.utils.ThreadLocalUtil;
import com.thinking.common.file.config.OSSProperties;
import com.thinking.common.file.domain.OSSResult;
import com.thinking.common.redis.service.RedisService;
import com.thinking.common.security.exception.ServiceException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : OSSService
 * @description : [阿里云OSS服务]
 * @createTime : [2025/1/21 18:52]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/21 18:52]
 * @updateRemark : [v1.0]
 */
@Slf4j
@Service
@RefreshScope //这个Bean支持动态刷新，即可以在不重启应用的情况下更新配置。
public class OSSService {

    @Autowired
    private OSSProperties ossProperties;

    @Resource
    private OSSClient ossClient;

    @Autowired
    private RedisService redisService;

    @Value("${file.max-time}")
    private int maxTime;

    @Value("${file.test}")
    private boolean test;


    //上传文件对外暴露接口
    public OSSResult uploadFile(MultipartFile file) throws Exception {
        if (!test){
            checkUploadCount();
        }
        InputStream inputStream = null;
        try {
            String fileName;
            if (file.getOriginalFilename() != null) {
                fileName = file.getOriginalFilename().toLowerCase();
            } else {
                fileName = Constants.UPLOAD_FILE_SUFFIX;
            }
            String extendName = fileName.substring(fileName.lastIndexOf(".") + 1);
            inputStream = file.getInputStream();
            return upload(extendName,inputStream);
        } catch (Exception e) {
            log.error(Constants.OSS_UPLOAD_FILE_ERROR, e);
            throw new ServiceException(ResultCode.FAILED_FILE_UPLOAD);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    //上传文件逻辑
    private OSSResult upload(String fileType, InputStream inputStream) {
        // key pattern: file/id.xxx, cannot start with /
        String key = ossProperties.getPathPrefix() + ObjectId.next() + "." + fileType;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
        PutObjectRequest putObjectRequest = new PutObjectRequest(ossProperties.getBucketName(), key, inputStream, objectMetadata);
        PutObjectResult putObjectResult;
        try {
            putObjectResult = ossClient.putObject(putObjectRequest);
        } catch (Exception e) {
            log.error("OSS put object error: {}", ExceptionUtil.stacktraceToOneLineString(e,Constants.STRING_LENGTH_PARAMETER));
            throw new ServiceException(ResultCode.FAILED_FILE_UPLOAD);
        }
        return assembleOSSResult(key, putObjectResult);
    }

    //组装OSS返回的文件结果
    private OSSResult assembleOSSResult(String key, PutObjectResult putObjectResult) {
        OSSResult ossResult = new OSSResult();
        if (putObjectResult == null || StrUtil.isBlank(putObjectResult.getRequestId())) {
            ossResult.setSuccess(false);
        } else {
            ossResult.setSuccess(true);
            ossResult.setName(FileUtil.getName(key));
        }
        return ossResult;
    }

    //检查上传数量
    private void checkUploadCount() {
        Long userId = ThreadLocalUtil.get(Constants.USER_ID, Long.class);
        Long times = redisService.getCacheMapValue(CacheConstants.USER_UPLOAD_TIMES_KEY, String.valueOf(userId), Long.class);
        if (times != null || times >= maxTime) {
            throw new ServiceException(ResultCode.FAILED_FILE_UPLOAD_TIME_LIMIT);
        }
        redisService.incrementHashValue(CacheConstants.USER_UPLOAD_TIMES_KEY, String.valueOf(userId), 1);
        if(times == 0) {
            long seconds = ChronoUnit.SECONDS.between
                    (LocalDateTime.now(), LocalDateTime.now()
                            .plusDays(1)
                            .withHour(0)
                            .withMinute(0)
                            .withNano(0));
            redisService.expire(CacheConstants.USER_UPLOAD_TIMES_KEY, seconds, TimeUnit.SECONDS);
        }
    }
}
