package com.pet.intellias.football.manager.server.operation.impl;

import com.pet.intellias.football.manager.server.operation.Command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Send extends Command {

    private static Logger logger = Logger.getLogger(Send.class.getName());

    public Send(SelectionKey key, Selector selector, String message) {
        super(key, selector, message);
    }

    @Override
    public int runCommand() {
        int bytes = 0;
        logger.info("sending data to client");
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        try {
            bytes = channel.write(buffer);
            logger.info("Data was send to server: " + message);
            key.interestOps(SelectionKey.OP_READ);
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
