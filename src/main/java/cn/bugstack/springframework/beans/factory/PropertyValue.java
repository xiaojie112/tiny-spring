package cn.bugstack.springframework.beans.factory;

/**
 * @description:
 * @author：肖杰
 * @date: 2023-03-22
 * bean 属性信息
 */
public class PropertyValue {

    private final String name;

    //这里的value可能是基本类型或者是引用类型，即BeanReference
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
