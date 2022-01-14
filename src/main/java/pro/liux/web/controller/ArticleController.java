package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.liux.web.service.TestService;

import java.io.File;

@RestController
public class ArticleController {
    @Autowired
    TestService testService;


    @PostMapping("markdown")
    public String getArticle(){
        File file = new File("");
        System.out.println(file.getAbsolutePath());
        return "# HelloWord";
    }


}
