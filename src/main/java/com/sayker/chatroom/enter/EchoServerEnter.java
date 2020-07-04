package com.sayker.chatroom.enter;

import com.sayker.chatroom.app.EchoServerApp;

/**
 * the server startup enter
 * @author sayker
 */
public class EchoServerEnter {

    public static void main(String[] args) throws Exception {

        System.out.println("========BEGIN THE ECHO  SERVER ========");
        new EchoServerApp(8081).run();

    }
}
