package pro.liux.web.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisHash;

@Data
@EqualsAndHashCode(callSuper = true)
@RedisHash("persons")
public class Article extends BaseModel {
    private String title;

    private String content;
}