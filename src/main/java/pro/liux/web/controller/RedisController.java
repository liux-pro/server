package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
public class RedisController {
    @Autowired
    private StringRedisTemplate template;

    private ListOperations<String, String> listOps;

    @GetMapping(path="redis")
    public String get(@RequestParam String key){
        return template.opsForValue().get(key);
    }
    @PostMapping("redis")
    public String set(String key,String value){
        template.opsForValue().set(key,value);
        return "ok";
    }
}
