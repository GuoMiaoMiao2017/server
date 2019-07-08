package com.xiongzehua.server.RpcFramework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * RPC服务
 */
public class RpcProvider {

    public static void export(Object service, int port) throws Exception {
        ServerSocket server = new ServerSocket(port);
        while(true) {
            Socket socket = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            System.out.println("服务端解析请求 ： ");
                            String methodName = input.readUTF();
                            Class<?>[] parameterTypes = (Class<?>[])input.readObject();
                            Object[] arguments = (Object[])input.readObject();
                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                            Method method = service.getClass().getMethod(methodName, parameterTypes);
                            Object result = method.invoke(service, arguments);
                            System.out.println("服务端响应 ：result = " + result);
                            output.writeObject(result);
                            output.close();
                        } finally {
                            input.close();
                            socket.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
