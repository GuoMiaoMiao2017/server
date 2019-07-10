package com.xiongzehua.server;
import com.xiongzehua.server.RpcFramework.RpcProvider;
import com.xiongzehua.server.service.RedisProvider;
import com.xiongzehua.server.service.RedisProviderImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author gmm
 * @ description
 * @ date create in 2019年6月25日14:55:03
 */
@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) throws Exception {
//        SpringApplication.run(ServerApplication.class, args);
//        RedisProvider service = RedisProviderImpl.getRedisProviderImpl();
        RpcProvider.export(1235);
    }
}

