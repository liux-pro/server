package pro.liux.web.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseModel {
    private Integer id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
