package org.example.v2.handler;

import org.example.RpcRequest;
import org.example.v2.Merdiator.Merdiator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {


    private Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        ObjectInputStream inputStream=null;
        ObjectOutputStream outputStream=null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();

            Merdiator intance = Merdiator.getIntance();

            Object res = intance.processor(rpcRequest);

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

}
