package com.pet.intellias.football.manager.client.impl;

import com.pet.intellias.football.manager.client.NIOClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class NIOClientImpl implements NIOClient {

    private static Logger logger = Logger.getLogger(NIOClientImpl.class.getName());

    public void createClient(String host, int port, String message){
        SocketChannel client = connect(new InetSocketAddress(host, port));
        send(client, message);
        String result = receive(client);
        logger.info("Received from server: " + result);
    }

    @Override
    public SocketChannel connect(InetSocketAddress address) {
        SocketChannel server = null;
        try {
            server = SocketChannel.open(address);
            logger.info("Connected to server: " + address.getHostName() + ":" + address.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return server;
    }

    @Override
    public void send(SocketChannel client, String message) {
        logger.info("Started sending data to server");
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        try {
            client.write(buffer);
            logger.info("Data was send to server: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String receive(SocketChannel client) {
        logger.info("Started receiving data from server");
        ByteBuffer buffer = ByteBuffer.allocate(16);
        int bytes = 0;
        try {
            bytes = client.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Read bytes from server: " + bytes);
        return new String(buffer.array()).trim();
    }
}
