package org.example.v2.client.proxy;

import org.example.v2.handler.RemoteInvocationHandler;
import org.example.v2.annotation.WjhReference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

@Component
public class ReferenceInvokeProxy implements BeanPostProcessor {

    @Autowired
    private RemoteInvocationHandler remoteInvocationHandler;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        Field[] declaredFields = bean.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            if(field.isAnnotationPresent(WjhReference.class)){

                Object proxy = Proxy.newProxyInstance(field.getType().getClassLoader(), new Class[]{field.getType()}, remoteInvocationHandler);
                try {
                    field.setAccessible(true);
                    field.set(bean,proxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
