package com.carpool.domain;

/**
 * Created by deado on 2016/12/21.
 */
public class MessageEntity {
    private String user;
    private String room;
    private String chatChontent;

    public MessageEntity() {
    }

    public MessageEntity(String user, String room, String chatChontent) {
        this.user = user;
        this.room = room;
        this.chatChontent = chatChontent;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getChatChontent() {
        return chatChontent;
    }

    public void setChatChontent(String chatChontent) {
        this.chatChontent = chatChontent;
    }
}
