package com.pet.intellias.football.manager.server.operation.impl;

import com.pet.intellias.football.manager.server.operation.ServerCommand;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Accept extends ServerCommand {

    private static Logger logger = Logger.getLogger(Accept.class.getName());

    public Accept(SelectionKey key, Selector selector, String message) {
        super(key, selector, message);
    }

    @Override
    public int runCommand() {
        logger.info("client was connected");
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel;
        try {
            channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
