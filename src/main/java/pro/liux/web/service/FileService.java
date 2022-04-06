package pro.liux.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * 下载远程图片，下载到本地并且上传到oss
     *
     * @param originalUrl 原始url
     * @return url
     */
    String convertImgUrl(String originalUrl);

    /**
     * 上传图片到oss，返回链接
     *
     * @param fileKey 文件名
     * @param image   图片字节
     * @return url
     */
    String uploadFile(String fileKey, byte[] image);

    /**
     * 上传文件到oss，返回链接
     *
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);

    /**
     * 生成文件直链
     *
     * @param fileKey 文件名
     * @return url
     */
    String getOssDirectLink(String fileKey);

    /**
     * 给url添加签名
     *
     * @param url  原始文件url
     * @param time 有效时间 秒
     * @return url
     */
    String getAuthedUrl(String url, Integer time);
}
