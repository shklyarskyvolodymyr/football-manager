package com.pet.intellias.football.manager.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import static org.junit.jupiter.api.Assertions.*;

class ServerRunnableTest {

    private String host = "localhost";
    private int port = 20000;
    ServerRunnable unit = new ServerRunnable(host, port);

    @Test
    void run() {
        new Thread(unit).start();
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
}