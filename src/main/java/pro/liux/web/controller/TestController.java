package pro.liux.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.liux.web.mapper.CityMapper;
import pro.liux.web.vo.City;
import pro.liux.web.vo.VditorImage;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @PostMapping("aaa")
    public VditorImage aaa() {
        VditorImage build = VditorImage.builder().errFiles(Arrays.asList("1", "2"))
                .succMap(new HashMap<String, String>() {{
                    put("1", "2");
                }}).build();

        return build;
    }

    @Autowired
    CityMapper cityMapper;
    @GetMapping("db")
    public Object testDB() {
        Collection<City> all = cityMapper.findAll();
        return all;
    }

}
