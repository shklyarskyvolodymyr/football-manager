package com.pet.intellias.football.manager.server;

public interface NIOServer {

    void bind();

    Runnable accept();

    void send();

    void receive();
}
