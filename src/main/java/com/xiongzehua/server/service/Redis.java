package com.xiongzehua.server.service;

public interface Redis {
    void set(String key, String value);
    String get(String key);
}
