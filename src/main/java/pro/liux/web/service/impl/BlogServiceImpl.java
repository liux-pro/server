package pro.liux.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.liux.web.client.S3Client;
import pro.liux.web.config.property.S3Property;
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
    S3Property s3Property;


    @Override
    public String convertImgUrl(String originalUrl) {

        return null;
    }


    @Override
    public String uploadImg(String fileKey, byte[] image) {
        s3Client.put(fileKey, image);
        String ossDirectLink = getOssDirectLink(fileKey, 1000);
        return ossDirectLink;
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
     * @param time    有限时间 秒
     * @return url
     */
    private String getOssDirectLink(String fileKey, Integer time) {
        String url = String.format("%s://%s/%s", s3Property.getProtocol(),
                s3Property.getCdnHost(), fileKey);
        url = url + "?e=" + ((System.currentTimeMillis() / 1000) + time);
        byte[] bytes = EncryptUtils.hmacSHA1(url, s3Property.getSecretKey().getBytes(StandardCharsets.UTF_8));
        String encodedString = Base64.getUrlEncoder().encodeToString(bytes);
        url = url + "&token=" + s3Property.getAccessKey() + ":" + encodedString;
        return url;
    }
}
