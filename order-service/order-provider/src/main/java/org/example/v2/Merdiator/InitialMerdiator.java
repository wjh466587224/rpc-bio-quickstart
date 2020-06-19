package org.example.v2.Merdiator;

import org.example.v2.annotation.WjhRemoteService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *
 */
@Component
public class InitialMerdiator implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(WjhRemoteService.class)){
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String key = bean.getClass().getInterfaces()[0].getName() + "." + method.getName();
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                Merdiator.map.put(key,beanMethod);
            }
        }
        return bean;
    }
}
