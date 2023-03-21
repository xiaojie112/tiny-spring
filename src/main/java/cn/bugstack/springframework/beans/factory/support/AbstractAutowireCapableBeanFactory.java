package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @description:
 * @author：肖杰
 * @date: 2023-03-21
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    //todo 这个策略类是什么东西, 是否可以考虑通过配置的方式切换初始化方式
//    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        //将创建好的bean对象添加到单例池中
        addSingleton(beanName, bean);
        return bean;
    }


    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        //得到对应的构造函数对象
        for (Constructor ctor : declaredConstructors) {
            //todo 待优化，这里是用类构造函数的参数个数来进行匹配，时间上参数类型也应当进行匹配
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        //调用对应的初始化对象的策略来创建对象 jdk and cglib
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}
