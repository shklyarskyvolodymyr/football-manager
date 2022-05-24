package com.pet.intellias.football.manager.client.impl;

import com.pet.intellias.football.manager.client.NIOClient;
import com.pet.intellias.football.manager.server.NIOServer;
import com.pet.intellias.football.manager.server.impl.NIOServerImpl;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NIOClientImplTest {

    private String host = "localhost";
    private int port = 10000;
    private String message = "test";
    private NIOServer server = new NIOServerImpl(new InetSocketAddress(host, port));


    @Test
    void send() {
        startServer();
        NIOClient unit = new NIOClientImpl(host, port);
        int sendBytes = unit.send(message);

        assertEquals(4, sendBytes);
    }

    @Test
    void receive() {
        startServer();
        NIOClient unit = new NIOClientImpl(host, port);
        unit.send(message);
        String result = unit.receive();

        assertEquals("Hi", result);
    }

    private void startServer(){
        new Thread(() -> server.createServer())
                .start();
    }
}