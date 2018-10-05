package com.honor.util;

import com.google.gson.Gson;

/**
 * Created by rongyaowen on 2018/10/5.
 * 将gson的方法封装成静态方法
 */
public final class GsonUtil {
    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String str, Class<T> c) {
        return gson.fromJson(str, c);
    }
}
