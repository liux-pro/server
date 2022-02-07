package pro.liux.web.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseModel {
    private Long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
