package pro.liux.web.vo;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;

import java.util.Map;

@Data
@Builder
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success() {
        return Result.builder().code(200).build();
    }

    public static Result fail(int code) {
        return Result.builder().code(code).build();
    }

}
