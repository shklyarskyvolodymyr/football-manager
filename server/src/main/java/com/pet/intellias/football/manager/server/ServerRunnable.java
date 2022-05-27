package com.pet.intellias.football.manager.server;

import com.pet.intellias.football.manager.server.domain.OperationValues;
import com.pet.intellias.football.manager.server.operation.Command;
import com.pet.intellias.football.manager.server.operation.impl.Accept;
import com.pet.intellias.football.manager.server.operation.impl.Receive;
import com.pet.intellias.football.manager.server.operation.impl.Send;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

public class ServerRunnable implements Runnable {

    private static Logger logger = Logger.getLogger(ServerRunnable.class.getName());
    private final Map<OperationValues, Command> dispatcherMethods = new HashMap<>();
    private static String message = "Hi";
    private InetSocketAddress address;
    private Selector selector;

    public ServerRunnable(String host, int port) {
        this.address = new InetSocketAddress(host, port);
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(address);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("server was created");
//            todo
            while (Thread.currentThread().isAlive()) {
                selector.select();
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();
                    initializeDispatcherMethods(key, selector, message);
                    dispatcherMethods.get(OperationValues.valueOf(key.readyOps()))
                            .runCommand();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeDispatcherMethods(SelectionKey key, Selector selector, String message) {
        dispatcherMethods.put(OperationValues.ACCEPTABLE, new Accept(key, selector, message));
        dispatcherMethods.put(OperationValues.WRITABLE, new Send(key, selector, message));
        dispatcherMethods.put(OperationValues.READABLE, new Receive(key, selector, message));
    }
}
