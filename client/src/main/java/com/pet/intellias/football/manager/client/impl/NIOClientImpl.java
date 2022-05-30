package com.pet.intellias.football.manager.client.impl;

import com.pet.intellias.football.manager.client.NIOClient;
import com.pet.intellias.football.manager.client.domain.ClientOperationValues;
import com.pet.intellias.football.manager.client.operation.ClientCommand;
import com.pet.intellias.football.manager.client.operation.impl.Receive;
import com.pet.intellias.football.manager.client.operation.impl.Send;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class NIOClientImpl implements NIOClient {

    private static Logger logger = Logger.getLogger(NIOClientImpl.class.getName());
    private final SocketChannel socketChannel;
    private final Map<ClientOperationValues, ClientCommand> dispatcherMethods = new HashMap<>();

    public NIOClientImpl(String host, int port) {
        this.socketChannel = bind(host, port);
    }

    @Override
    public void startClient(String message) {
        initializeDispatcherMethods(socketChannel, message);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                dispatcherMethods.get(ClientOperationValues.SEND);
                dispatcherMethods.get(ClientOperationValues.RECEIVE);
            }).start();
        }
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

    private void initializeDispatcherMethods(SocketChannel socketChannel, String message) {
        dispatcherMethods.put(ClientOperationValues.RECEIVE, new Receive(socketChannel, null));
        dispatcherMethods.put(ClientOperationValues.SEND, new Send(socketChannel, message));
    }
}
