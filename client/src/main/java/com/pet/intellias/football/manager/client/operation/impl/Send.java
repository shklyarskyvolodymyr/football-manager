package com.pet.intellias.football.manager.client.operation.impl;

import com.pet.intellias.football.manager.client.operation.ClientCommand;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Send extends ClientCommand {

    private static Logger logger = Logger.getLogger(Send.class.getName());

    public Send(SocketChannel socketChannel, String message) {
        super(socketChannel, message);
    }

    @Override
    public int runCommand() {
        logger.info("Started sending data to server");
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        int bytes = 0;
        try {
            bytes = socketChannel.write(buffer);
            logger.info("Data was send to server: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
