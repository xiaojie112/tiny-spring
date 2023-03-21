package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
/**
 * @description:
 * @author：肖杰
 * @date: 2023-03-21
 */

/**
 * 实例化策略
 */
public interface InstantiationStrategy {

    //todo 个人觉得这个beanName没有什么意义，没有用到
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
