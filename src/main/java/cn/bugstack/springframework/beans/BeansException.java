package cn.bugstack.springframework.beans;

/**
 * @description:
 * @author：肖杰
 * @date: 2023-03-21
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
