package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pro.liux.web.client.DateTestClient;
import pro.liux.web.service.TestService;
import pro.liux.web.vo.Result;
import pro.liux.web.vo.VditorImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@RestController
@CrossOrigin
public class ArticleController {
    @Autowired
    TestService testService;

    @Autowired
    DateTestClient dateTestClient;


    @PostMapping("markdown")
    public String getArticle() {
        File file = new File("");
        System.out.println(file.getAbsolutePath());
        return "# HelloWord";
    }

    private String fileLocation = "";

    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file[]") MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
        FileOutputStream f = new FileOutputStream(fileLocation);
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();
        VditorImage vditorImage = VditorImage.
                builder()
                .succMap(Collections.singletonMap(file.getOriginalFilename(), "https://s3.bmp.ovh/imgs/2022/01/de5950621639e899.jpg"))
                .build();
        Result result = Result.success();
        result.setData(vditorImage);
        return result;
    }

    @PostMapping("aaa")
    public Object aaa() {
        Object post = dateTestClient.getPost();
        return post;
    }
}
