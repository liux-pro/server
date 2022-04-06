package pro.liux.web.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Article extends BaseModel {
    private String title;

    private String content;
}