package com.pet.intellias.football.manager.client;



public interface NIOClient {

    void send(String message);

    String receive();
}
