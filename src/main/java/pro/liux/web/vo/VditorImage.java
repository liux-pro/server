package pro.liux.web.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class VditorImage {
    private Map<String,String> succMap;
    private List<String> errFiles;
}
