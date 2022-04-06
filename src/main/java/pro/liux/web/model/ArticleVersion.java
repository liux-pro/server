package pro.liux.web.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleVersion extends BaseModel {
    private Long articleId;

    private String title;

    private String content;
}