package com.sayker.chatroom.utility;

import com.sayker.chatroom.dto.MessageDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * the chatroom utilities
 *
 * @author sayker
 */
public class ChatroomUtilies {

    public static String encodeMsg(MessageDTO message) {
        return message.getUsername() + "~" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message.getSentTime())) + "~" + message.getMsg();
    }
    public static String formatDateTime(Date time){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    public static Date parseDateTime(String time){
        try{
            return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
        }catch (ParseException e){
            System.out.println("error parse Date");
            return null;
        }
    }

    public static void printMsg(MessageDTO msg) {
        System.out.println("=================================================================================================");
        System.out.println("                      " + ChatroomUtilies.formatDateTime(msg.getSentTime()) + "                     ");
        System.out.println(msg.getUsername() + ": " + msg.getMsg());
        System.out.println("=================================================================================================");

    }

}
