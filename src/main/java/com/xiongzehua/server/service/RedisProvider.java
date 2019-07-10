package com.xiongzehua.server.service;

public interface RedisProvider {
    void set(String key, String value);
    String get(String key);
}
