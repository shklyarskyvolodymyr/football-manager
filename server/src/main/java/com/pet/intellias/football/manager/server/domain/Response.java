package com.pet.intellias.football.manager.server.domain;


public class Response {

    private int bytes;
    private String message;

    public Response(int bytes, String message) {
        this.bytes = bytes;
        this.message = message;
    }

    public Response() {
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public int getBytes() {
        return bytes;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
