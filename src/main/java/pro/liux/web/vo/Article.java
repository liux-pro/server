package pro.liux.web.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Article extends BaseDO{
    private String title;
    private String content;
}