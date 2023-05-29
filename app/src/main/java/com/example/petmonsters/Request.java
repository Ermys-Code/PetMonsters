package com.example.petmonsters;

public class Request {

    private String from;
    private String to;


    Request(String from, String to){
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}