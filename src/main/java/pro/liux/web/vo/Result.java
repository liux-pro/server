package pro.liux.web.vo;

import lombok.Data;

@Data
public class Result<E> {
    private Integer code;
    private String msg;
    private Object data;

    public static <T> Result<T> success(T data) {
        Result<T> tResult = new Result<T>();
        tResult.setCode(0);
        tResult.setData(data);
        return tResult;
    }

    public static <T> Result<T> fail(int code) {
        Result<T> tResult = new Result<T>();
        tResult.setCode(code);
        return tResult;
    }


    public static <T> Result<T> badRequest() {
        Result<T> tResult = new Result<T>();
        tResult.setCode(400);
        return tResult;
    }
    public static <T> Result<T> badRequest(T msg) {
        Result<T> tResult = new Result<T>();
        tResult.setCode(400);
        tResult.setData(msg);
        return tResult;
    }


}
