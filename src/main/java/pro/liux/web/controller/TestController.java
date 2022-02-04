package pro.liux.web.controller;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.liux.web.client.DateTestClient;
import pro.liux.web.vo.VditorImage;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    DateTestClient dateTestClient;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("aaa")
    public VditorImage aaa() {
        Object post = dateTestClient.getPost();

        VditorImage build = VditorImage.builder().errFiles(Arrays.asList("1", "2"))
                .succMap(new HashMap<String, String>() {{
                    put("1", "2");
                }}).build();

        return build;
    }

    @GetMapping("db")
    public Map testDB() {
        Map<String, Object> stringObjectMap =
                jdbcTemplate.queryForMap("select * from city");

        return stringObjectMap;
    }

    @GetMapping("okhttp")
    public Object testHttp(@RequestParam("url") String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string() + "";
        }
    }

}
