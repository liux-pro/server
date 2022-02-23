package pro.liux.web.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisHash;

@Data
@EqualsAndHashCode(callSuper = true)
@RedisHash("article")
public class Article extends BaseModel {
    private String title;

    private String content;
}