package com.pet.intellias.football.manager.client;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public interface NIOClient {

    void createClient(String host, int port, String message);

    SocketChannel connect(InetSocketAddress address);

    void send(SocketChannel client, String message);

    String receive(SocketChannel client);
}
