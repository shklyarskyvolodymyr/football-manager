package com.pet.intellias.football.manager.server.impl;


import com.pet.intellias.football.manager.server.NIOServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NIOServerImplTest {

    private static String host = "localhost";
    private static int port = 10000;
    private static NIOServer unit = new NIOServerImpl(new InetSocketAddress(host, port));

    @BeforeAll
    public static void init(){
        new Thread(() -> unit.createServer())
                .start();
    }

    @Test
    void createServer() {
        SocketChannel client = createClient();

        assertTrue(client.isConnected());
    }

    @Test
    void receive() {
        SocketChannel client = createClient();
        int receivedBytes = writeToServer(client);

        assertEquals(4, receivedBytes);
    }

    @Test
    void send() {
        SocketChannel client = createClient();
        writeToServer(client);
        String result = receiveFromServer(client);

        assertEquals("Hi", result);
    }

    @Test
    void accept() {
        SocketChannel client = createClient();

        assertTrue(client.isConnected());
    }

    private SocketChannel createClient(){
        SocketChannel client = null;
        try {
            client = SocketChannel.open(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    private int writeToServer(SocketChannel client) {
        ByteBuffer buffer = ByteBuffer.wrap("test".getBytes());
        int result = 0;
        try {
            result = client.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String receiveFromServer(SocketChannel client) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        try {
            client.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = new String(buffer.array()).trim();
        return result;
    }
}