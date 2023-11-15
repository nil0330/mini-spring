package cn.bugstack.springframework.beans.factory.support;

import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 * <p>
 * Bean 实例化策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
/**
 * 这个接口的目的是提供一种可插拔的、可定制的Bean实例化策略。通过实现这个接口，你可以定义自己的实例化逻辑，以适应不同的需求，比如支持特定的依赖注入方式、实例化前的处理等。在Spring框架中，不同的InstantiationStrategy 实现可以被配置，以适应不同的场景和需求。
 */