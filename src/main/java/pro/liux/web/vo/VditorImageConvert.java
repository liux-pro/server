package pro.liux.web.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VditorImageConvert {
    private String originalURL;
    private String url;
}
