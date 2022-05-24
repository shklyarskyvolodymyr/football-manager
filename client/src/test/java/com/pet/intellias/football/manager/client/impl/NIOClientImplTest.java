package com.pet.intellias.football.manager.client.impl;

import com.pet.intellias.football.manager.client.NIOClient;
import com.pet.intellias.football.manager.server.NIOServer;
import com.pet.intellias.football.manager.server.impl.NIOServerImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NIOClientImplTest {

    private static String host = "localhost";
    private static int port = 10000;
    private static String message = "test";
    private NIOClient unit;
    private static NIOServer server = new NIOServerImpl(new InetSocketAddress(host, port));


    @BeforeAll
    public static void init(){
        new Thread(() -> server.createServer())
                .start();
    }

    @Test
    void send() {
        unit = new NIOClientImpl(host, port);
        int sendBytes = unit.send(message);

        assertEquals(4, sendBytes);
    }

    @Test
    void receive() {
        unit = new NIOClientImpl(host, port);
        unit.send(message);
        String result = unit.receive();

        assertEquals("Hi", result);
    }
}