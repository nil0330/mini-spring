package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.BeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 * <p>
 * BeanDefinition注册表接口
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        // 尝试从单例缓存中获取已经创建的Bean实例
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;// 如果已经存在，则直接返回
        }
        // 如果缓存中没有对应的实例，就尝试获取Bean的定义
        BeanDefinition beanDefinition = getBeanDefinition(name);
        // 根据Bean的定义和传入的参数数组创建新的Bean实例
        return (T) createBean(name, beanDefinition, args);
    }

    /**
     * 这是一个泛型方法，使用 <T> 表示泛型。它接受一个字符串参数 name 表示Bean的名称，以及一个 Object[] args 表示传递给Bean构造函数的参数数组。
     * 返回类型是 T，因为它是泛型方法，可以根据实际的调用上下文确定具体的类型。
     * @param beanName
     * @return
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
