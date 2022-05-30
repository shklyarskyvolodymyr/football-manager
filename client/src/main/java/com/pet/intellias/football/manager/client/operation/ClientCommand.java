package com.pet.intellias.football.manager.client.operation;

import java.nio.channels.SocketChannel;

public abstract class ClientCommand {

    protected SocketChannel socketChannel;
    protected String message;

    public ClientCommand(SocketChannel socketChannel, String message) {
        this.message = message;
        this.socketChannel = socketChannel;
    }

    public abstract int runCommand();
}
