package cn.bugstack.springframework.beans;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * 定义 Bean 异常
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
