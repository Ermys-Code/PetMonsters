package com.example.petmonsters;

public class Message {
    private String from;
    private String to;
    private String title;
    private String message;

    public Message(String from, String to, String title, String message){
        this.from = from;
        this.to = to;
        this.title = title;
        this.message = message;
    }

    public String getFrom(){
        return from;
    }

    public String getTo(){
        return to;
    }

    public String getTitle(){
        return title;
    }

    public String getMessage(){
        return message;
    }
}