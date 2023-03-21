package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

/**
 * @description:
 * @author：肖杰
 * @date: 2023-03-21
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    //todo args的作用是什么
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    //todo 这里使用泛型的意义是什么
    protected <T> T doGetBean(final String name, final Object[] args) {
        //首先尝试从单例池中获取bean对象
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }
        //根据bean的名字获取bean定义
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }



    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
