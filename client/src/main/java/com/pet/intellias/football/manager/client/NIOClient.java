package com.pet.intellias.football.manager.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NIOClient {

    private static Logger logger = Logger.getLogger(NIOClient.class.getName());

    public static void createClient(String host, int port){
        InetSocketAddress address = new InetSocketAddress(host, port);
        try (SocketChannel client = SocketChannel.open(address)) {
            logger.info("Connected to server");
            ByteBuffer buffer = ByteBuffer.wrap("Hello".getBytes());
            client.write(buffer);
            logger.info("Message was send to buffer");
            buffer.clear();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException occurs");
        }
        logger.info("Connection closed");
    }
}
