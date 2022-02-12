package pro.liux.web.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@RedisHash("persons")
public class Article extends BaseModel {
    @Size(min=2, message="title should have at least 2 characters")
    private String title;

    @Size(min=2, message="content should have at least 2 characters")
    private String content;
}