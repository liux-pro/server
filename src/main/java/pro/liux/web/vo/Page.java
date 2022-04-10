package pro.liux.web.vo;

import lombok.Data;

import java.util.List;

@Data
public class Page<E> {
    public Page(List<E> list) {
        this.list = list;
    }
    private Long total;
    private List<E> list;
}
