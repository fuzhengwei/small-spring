package cn.bugstack.springframework.beans;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 *
 * bean 属性信息
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
