package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        // 获取Bean的Class对象
        Class clazz = beanDefinition.getBeanClass();
        try {
            // 使用反射实例化Bean
            if (null != ctor) {
                // 如果存在指定的构造函数，则使用指定的构造函数进行实例化
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                // 否则，使用无参构造函数进行实例化
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            // 捕获反射相关的异常，并抛出自定义的 BeansException
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }

}
