package com.pet.intellias.football.manager.client;



public interface NIOClient {

    int send(String message);

    String receive();
}
