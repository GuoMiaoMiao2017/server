package com.xiongzehua.server.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class RedisProviderImpl implements RedisProvider {

    private static HashMap<String, String> hashMap = new HashMap<>();
    private static RedisProviderImpl redisProviderImpl = new RedisProviderImpl();
    private RedisProviderImpl() {    }

    public static RedisProviderImpl getRedisProviderImpl() {
        return redisProviderImpl;
    }

    public void set(String key, String value) {
        hashMap.put(key, value);
        System.out.println("set成功数据:" + hashMap.toString());
    }

    public String get(String key) {
        String value = hashMap.get(key);
        System.out.println("get成功数据:" + value.toString());
        return value;
    }
}
