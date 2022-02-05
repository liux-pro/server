package pro.liux.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.liux.web.vo.VditorImage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("aaa")
    public VditorImage aaa() {
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

}
