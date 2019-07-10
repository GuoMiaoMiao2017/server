package com.xiongzehua.server.RpcFramework;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiongzehua.server.service.RedisProvider;
import com.xiongzehua.server.service.RedisProviderImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * RPC服务
 */
public class RpcProvider {

    public static void export(int port) throws Exception {
        ServerSocket server = new ServerSocket(port);
        while(true) {
            // 监听Socket请求
            Socket socket = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            System.out.println("服务端解析请求 ： ");
                            byte[] buf = new byte[1024];
//                            Object object = input.readObject();
                            int len = 0;
                            String result = "";
                            while((len = input.read(buf))!=-1){
                                System.out.println(new String(buf,0,len));
                                result = new String(buf,0,len);
                            }
                            System.out.println("服务端接收到数据 ： result = " + result);
                            String method = JSONObject.parseObject(result).getString("method");
                            String parameters = JSONObject.parseObject(result).getString("parameter");

                            String[] param = parameters.split(";");
                            for (int i = 0; i < param.length; i ++) {
                                param[i] = param[i].replace("String,", "");
                            }

                            RedisProvider service = RedisProviderImpl.getRedisProviderImpl();
                            if (method.equals("set")) {
                                service.set(param[0], param[1]);
                            }

                            Map<String, String> map = new HashMap<>();
                            map.put("code", "1");
                            map.put("msg", "set成功");

                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                            output.write(JSON.toJSONBytes(map));
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
