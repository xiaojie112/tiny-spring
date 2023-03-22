package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.PropertyValue;
import cn.bugstack.springframework.beans.factory.PropertyValues;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanUtil;

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
            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
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

    /**
     * Bean 属性填充
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if(propertyValues == null)return; // 无需属性填充
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    //递归获取bean对象
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                //todo 使用hutool工具包，底层实际上是通过反射实现
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

}
