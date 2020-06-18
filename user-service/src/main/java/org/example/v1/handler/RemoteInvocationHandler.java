package org.example.v1.handler;

import org.example.RpcRequest;
import org.example.v1.sport.RpcNetTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }
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
