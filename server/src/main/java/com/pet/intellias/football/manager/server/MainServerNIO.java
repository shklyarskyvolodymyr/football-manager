package com.pet.intellias.football.manager.server;


import com.pet.intellias.football.manager.server.impl.NIOServerImpl;

public class MainServerNIO {

    private static String host = "localhost";
    private static int port = 20000;

    public static void main(String[] args) {
        new NIOServerImpl(host, port).createServer();
    }
}
