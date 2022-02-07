package pro.liux.web.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Article extends BaseModel {
    private String title;

    private String content;
}