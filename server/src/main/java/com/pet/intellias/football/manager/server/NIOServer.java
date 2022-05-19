package com.pet.intellias.football.manager.server;

public interface NIOServer {

    void createServer();

    Runnable accept();

    void send();

    void receive();
}
