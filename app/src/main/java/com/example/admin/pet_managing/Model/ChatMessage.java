package com.example.admin.pet_managing.Model;

public class ChatMessage {
    String messageText;
    String messageUser;
    long messageTime;

    public ChatMessage() {
    }

    public ChatMessage(String messageText, String messageUser,long messageTime) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.messageTime=messageTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
