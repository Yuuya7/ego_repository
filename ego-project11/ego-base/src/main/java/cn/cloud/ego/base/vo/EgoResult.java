package cn.cloud.ego.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EgoResult {
    // 响应业务状态
    private Integer status;
    // 响应消息
    private String msg;
    // 响应中的数据
    private Object data;

    public EgoResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static EgoResult build(Integer status, String msg, Object data) {
        return new EgoResult(status, msg, data);
    }

    public static EgoResult build(Integer status, String msg) {
        return new EgoResult(status, msg, null);
    }

    public static EgoResult ok(Object data) {
        return new EgoResult(data);
    }

    public static EgoResult ok() {
        return new EgoResult(null);
    }
}
