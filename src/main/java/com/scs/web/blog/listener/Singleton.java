package com.scs.web.blog.listener;

/**
 * @ClassName Singleton
 * @Description TODO
 * @Author ding
 * @Date 2019/11/21 8:43
 * @Version 1.0
 **/
public class Singleton {
    private static Singleton singleton = null;
    private Singleton(){

    }
    public static Singleton getSingleton(){
        if(singleton != null){

        }
        return singleton;
    }
}
