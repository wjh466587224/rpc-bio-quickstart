package org.example.v2.Merdiator;

import org.example.RpcRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Merdiator {

    public static Map<String ,BeanMethod> map=new ConcurrentHashMap<>();


    private volatile static Merdiator instance;

    private Merdiator(){}

    public static Merdiator getIntance(){
        if(instance==null){
            synchronized (Merdiator.class){
                if(instance==null){
                    instance= new Merdiator();
                }
            }
        }
        return instance;
    }

    public Object processor(RpcRequest rpcRequest){
        String key = rpcRequest.getClassName()+"."+rpcRequest.getMethodName();

        BeanMethod beanMethod = map.get(key);

        if(beanMethod==null){
            return null;
        }
        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();

        try {
            return method.invoke(bean,rpcRequest.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

}
