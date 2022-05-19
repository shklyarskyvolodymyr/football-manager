package com.pet.intellias.football.manager.server;


import com.pet.intellias.football.manager.server.impl.NIOServerImpl;

import java.net.InetSocketAddress;

public class MainServerNIO {

    private static String host = "localhost";
    private static int port = 20000;

    public static void main(String[] args) {
        NIOServer server = new NIOServerImpl(new InetSocketAddress(host, port));
        new Thread(server::bind)
                .start();
    }
}
