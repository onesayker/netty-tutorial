package com.sayker.chatroom.enter;

import com.sayker.chatroom.app.EchoServerApp;
import com.sayker.chatroom.app.TimeServerApp;

/**
 * the server startup enter
 * @author sayker
 */
public class TimeServerEnter {

    public static void main(String[] args) throws Exception {

        System.out.println("========BEGIN THE Time  SERVER ========");
        new TimeServerApp(8082).run();

    }
}
