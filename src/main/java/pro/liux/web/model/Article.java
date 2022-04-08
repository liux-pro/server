package pro.liux.web.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisHash;

@Data
@EqualsAndHashCode(callSuper = true)
@RedisHash("Article")
public class Article extends BaseModel {
    private Long articleVersionId;

    private String title;

    /**
     * 简略内容
     */
    private String mainContent;

    /**
     * 0显示，1隐藏
     */
    private Boolean isDelete;
}