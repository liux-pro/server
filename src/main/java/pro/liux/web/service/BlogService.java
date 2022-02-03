package pro.liux.web.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface BlogService {
    /**
     * 下载远程图片，下载到本地并且上传到oss
     * @param originalUrl 原始url
     * @return url
     */
    String convertImgUrl(String originalUrl);

    /**
     * 上传图片到oss，返回链接
     * @param fileKey 文件名
     * @param image 图片字节
     * @return url
     */
    String uploadImg(String fileKey,byte[] image);
    /**
     * 上传图片到oss，返回链接
     * @param file 表单 图片
     * @return url
     */
    String uploadImg(MultipartFile file);
}
