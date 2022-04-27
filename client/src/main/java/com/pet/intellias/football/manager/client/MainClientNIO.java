package com.pet.intellias.football.manager.client;


public class MainClientNIO {

    private static String host = "localhost";
    private static int port = 20000;

    public static void main(String[] args) {
        NIOClient.createClient(host, port);
    }
}
