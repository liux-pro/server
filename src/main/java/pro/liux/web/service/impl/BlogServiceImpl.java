package pro.liux.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.liux.web.client.S3Client;
import pro.liux.web.config.property.OSS;
import pro.liux.web.service.BlogService;
import pro.liux.web.utils.EncryptUtils;
import pro.liux.web.utils.UUID;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    S3Client s3Client;

    @Autowired
    OSS OSS;


    @Override
    public String convertImgUrl(String originalUrl) {

        return null;
    }


    @Override
    public String uploadImg(String fileKey, byte[] image) {
        s3Client.put(fileKey, image);
        return getOssDirectLink(fileKey);
    }

    @Override
    public String uploadImg(MultipartFile file) {
        String uuid = UUID.getUUID();


        return null;
    }

    /**
     * 生成文件直链
     *
     * @param fileKey 文件名
     * @return url
     */
    public String getOssDirectLink(String fileKey) {
        return String.format("%s://%s/%s", OSS.getProtocol(),
                OSS.getCdnHost(), fileKey);
    }

    /**
     * 给url添加签名
     *
     * @param url  原始文件url
     * @param time 有效时间 秒
     * @return url
     */
    public String getAuthedUrl(String url, Integer time) {
        url = url + "?e=" + ((System.currentTimeMillis() / 1000) + time);
        byte[] bytes = EncryptUtils.hmacSHA1(url, OSS.getSecretKey().getBytes(StandardCharsets.UTF_8));
        String encodedString = Base64.getUrlEncoder().encodeToString(bytes);
        url = url + "&token=" + OSS.getAccessKey() + ":" + encodedString;
        return url;
    }
}
