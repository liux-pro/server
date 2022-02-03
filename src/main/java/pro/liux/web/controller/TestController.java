package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.liux.web.client.DateTestClient;
import pro.liux.web.vo.VditorImage;

import java.util.Arrays;
import java.util.HashMap;

@RestController
public class TestController {
    @Autowired
    DateTestClient dateTestClient;

    @PostMapping("aaa")
    public VditorImage aaa() {
        Object post = dateTestClient.getPost();

        VditorImage build = VditorImage.builder().errFiles(Arrays.asList("1", "2"))
                .succMap(new HashMap<String, String>() {{
                    put("1", "2");
                }}).build();

        return build;
    }
}
