package com.sayker.chatroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * the request and response message structure
 * @author sayker
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String username;
    private Date sentTime;
    private String msg;
}
