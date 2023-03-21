package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;
/**
 * @description:
 * @author：肖杰
 * @date: 2023-03-21
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    //todo 暂未具体搞清楚cglib的执行过程
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        //todo Cglib通过继承来实现代理类对象
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        //调用空参构造函数
        if (null == ctor) return enhancer.create();
        //调用有参构造函数
        return enhancer.create(ctor.getParameterTypes(), args);
    }

}
