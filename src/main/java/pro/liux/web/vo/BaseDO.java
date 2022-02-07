package pro.liux.web.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDO {
    private Integer id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
