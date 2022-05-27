package com.pet.intellias.football.manager.server.operation.impl;

import com.pet.intellias.football.manager.server.operation.Command;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Receive extends Command {

    private static Logger logger = Logger.getLogger(Receive.class.getName());

    public Receive(SelectionKey key, Selector selector, String message) {
        super(key, selector, message);
    }

    @Override
    public int runCommand() {
        logger.info("Starting reading data from client");
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        int bytes = 0;
        try {
            bytes = channel.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Read bytes from client: " + bytes);
        String result = new String(buffer.array()).trim();
        logger.info("Received from client: " + result);
        key.interestOps(SelectionKey.OP_WRITE);
        return bytes;
    }
}
