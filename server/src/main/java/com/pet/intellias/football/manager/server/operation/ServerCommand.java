package com.pet.intellias.football.manager.server.operation;


import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public abstract class ServerCommand {

    protected SelectionKey key;
    protected Selector selector;
    protected String message;

    public ServerCommand(SelectionKey key, Selector selector, String message){
        this.key = key;
        this.selector = selector;
        this.message = message;
    }

    abstract public int runCommand();
}
