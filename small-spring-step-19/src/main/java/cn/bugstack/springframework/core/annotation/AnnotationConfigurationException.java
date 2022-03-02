package cn.bugstack.springframework.core.annotation;

/**
 * @author zhangdd on 2022/2/27
 */
public class AnnotationConfigurationException extends RuntimeException{
    public AnnotationConfigurationException(String message) {
        super(message);
    }

    public AnnotationConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
