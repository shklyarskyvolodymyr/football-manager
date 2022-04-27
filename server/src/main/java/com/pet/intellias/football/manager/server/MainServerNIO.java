package com.pet.intellias.football.manager.server;


public class MainServerNIO {

    private static String host = "localhost";
    private static int port = 20000;

    public static void main(String[] args) {
        NIOServer.createSocket(host, port);
    }
}
