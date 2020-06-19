package org.example.v2.handler;

import org.example.RpcRequest;
import org.example.v2.sport.RpcNetTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Component
public class RemoteInvocationHandler implements InvocationHandler {

    @Value("${gp.host}")
    private String host;
    @Value("${gp.port}")
    private int port;

    public RemoteInvocationHandler() {}
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        RpcRequest request=new RpcRequest();
        request.setArgs(args);
        request.setClassName(method.getDeclaringClass().getName());
        request.setTypes(method.getParameterTypes()); //参数的类型
        request.setMethodName(method.getName());
        return rpcNetTransport.send(request);

    }
}
