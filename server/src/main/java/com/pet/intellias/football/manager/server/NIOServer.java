package com.pet.intellias.football.manager.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface NIOServer {

    void bind();

    void accept(SelectionKey key) throws IOException;

    void send(SelectionKey key, String message) throws IOException;

    void receive(SelectionKey key) throws IOException;
}
