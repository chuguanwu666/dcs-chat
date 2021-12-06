package com.wuge.chat.Local;

import java.sql.PreparedStatement;
import java.util.Map;

public class LocalUser {
    public static ThreadLocal<Map> threadLocal=new ThreadLocal<>();
    public static void setThreadLocal(Map map){
        threadLocal.set(map);
    }
    public static Map getUser(){
        return threadLocal.get();
    }
}
