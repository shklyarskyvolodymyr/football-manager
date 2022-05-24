package com.pet.intellias.football.manager.client.impl;

import com.pet.intellias.football.manager.client.NIOClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class NIOClientImpl implements NIOClient {

    private static Logger logger = Logger.getLogger(NIOClientImpl.class.getName());
    private final SocketChannel client;

    public NIOClientImpl(String host, int port) {
        this.client = bind(host, port);
    }

    @Override
    public int send(String message) {
        logger.info("Started sending data to server");
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        int bytes = 0;
        try {
            bytes = client.write(buffer);
            logger.info("Data was send to server: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public String receive() {
        logger.info("Started receiving data from server");
        ByteBuffer buffer = ByteBuffer.allocate(16);
        try {
            client.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = new String(buffer.array()).trim();
        logger.info("Received from server: " + result);
        return result;
    }

    private SocketChannel bind(String host, int port){
        SocketChannel client = null;
        try {
            client = SocketChannel.open(new InetSocketAddress(host, port));
            logger.info("Connected to server: " + host + ":" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }
}
