package pro.legend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.legend.web.client.NodeClient;
import pro.legend.web.service.TestService;

import java.io.File;
import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    TestService testService;

    @Autowired
    NodeClient nodeClient;
    @PostMapping("markdown")
    public String getArticle(){
        File file = new File("");
        System.out.println(file.getAbsolutePath());

        return "# HelloWord";
    }

    @GetMapping
    public Object get(){
        Map map = nodeClient.get();
        return map;
    }

}
