package com.pet.intellias.football.manager.client;


import com.pet.intellias.football.manager.client.impl.NIOClientImpl;

public class MainClientNIO {

    private static String host = "localhost";
    private static int port = 20000;
    private static String message = "Hello";

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            NIOClient client = new NIOClientImpl(host, port);
            new Thread(() -> {
            client.send(message);
            client.receive();
            })
            .start();
        }
    }
}
