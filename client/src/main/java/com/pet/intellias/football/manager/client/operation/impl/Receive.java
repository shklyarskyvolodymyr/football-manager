package com.pet.intellias.football.manager.client.operation.impl;

import com.pet.intellias.football.manager.client.operation.ClientCommand;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Receive extends ClientCommand {

    private static Logger logger = Logger.getLogger(Receive.class.getName());

    public Receive(SocketChannel socketChannel, String message) {
        super(socketChannel, message);
    }

    @Override
    public int runCommand() {
        logger.info("Started receiving data from server");
        ByteBuffer buffer = ByteBuffer.allocate(16);
        int bytes = 0;
        try {
            bytes = socketChannel.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = new String(buffer.array()).trim();
        logger.info("Received from server: " + result);
        return bytes;
    }
}
