package com.bigevent.utils;

public class ThreadLocalUtils {

    private static final ThreadLocal<String> threadLocal=new ThreadLocal();
    public static void setCurrentUserName(String value) {
        threadLocal.set(value);
    }
    public static String getCurrentUserName() {
        return threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }

}
