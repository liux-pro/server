package pro.liux.web.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return Result.builder().code(0).data(data).build();
    }

    public static Result fail(int code) {
        return Result.builder().code(code).build();
    }

    public static Result badRequest() {
        return Result.builder().code(400).build();
    }


}
