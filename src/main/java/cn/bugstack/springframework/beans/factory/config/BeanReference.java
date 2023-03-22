package cn.bugstack.springframework.beans.factory.config;

/**
 * @description:
 * @author：肖杰
 * @date: 2023-03-22
 * Bean 的引用
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
