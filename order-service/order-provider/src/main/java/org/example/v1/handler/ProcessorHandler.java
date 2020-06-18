package org.example.v1.handler;

import org.example.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {


    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }


    @Override
    public void run() {
        ObjectInputStream inputStream=null;
        ObjectOutputStream outputStream=null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            Object res = invoke(rpcRequest);

            System.out.println("服务端的执行结果："+res);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(res);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<?> clazz = Class.forName(rpcRequest.getClassName());

        Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());

        return method.invoke(service,rpcRequest.getArgs());
    }
}
