package com.pet.intellias.football.manager.client.impl;

import com.pet.intellias.football.manager.client.NIOClient;
import com.pet.intellias.football.manager.server.NIOServer;
import com.pet.intellias.football.manager.server.impl.NIOServerImpl;
import org.junit.jupiter.api.Test;


class NIOClientImplTest {

    private String host = "localhost";
    private int port = 12000;
    private String message = "test";
    private NIOClient unit;
    private NIOServer server = new NIOServerImpl(host, port);

    @Test
    void send() {
        startServer();
        unit = new NIOClientImpl(host, port);
        unit.startClient(message);
    }

    private void startServer(){
        new Thread(() -> server.createServer())
                .start();
    }
}