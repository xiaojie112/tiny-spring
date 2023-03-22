package cn.bugstack.springframework.beans.factory.config;

import cn.bugstack.springframework.beans.factory.PropertyValues;

/**
 * @description:
 * @author：肖杰
 * @date: 2023-03-21
 */
@SuppressWarnings({"rawtypes"})
public class BeanDefinition {
    private PropertyValues propertyValues;

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition(PropertyValues propertyValues, Class beanClass) {
        this.propertyValues = propertyValues;
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
