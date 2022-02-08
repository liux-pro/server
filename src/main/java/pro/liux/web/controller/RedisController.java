package pro.liux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import pro.liux.web.mapper.ArticleRedisRepository;
import pro.liux.web.model.Article;


@RestController
public class RedisController {
    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private ArticleRedisRepository articleRedisRepository;

    @PostMapping(path="redisData")
    public Article setArticleRedisRepository(@RequestBody Article article){
        article.setId(1234L);
        return articleRedisRepository.save(article);
    }

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
