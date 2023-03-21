package cn.bugstack.springframework.beans.factory;

import cn.bugstack.springframework.beans.BeansException;

/**
 * @description:
 * @author：肖杰
 * @date: 2023-03-21
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;
}
