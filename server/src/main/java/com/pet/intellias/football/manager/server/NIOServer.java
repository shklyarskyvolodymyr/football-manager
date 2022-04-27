package com.pet.intellias.football.manager.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NIOServer {

    private static Logger logger = Logger.getLogger(NIOServer.class.getName());

    public static void createSocket(String host, int port) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
             Selector selector = Selector.open()) {
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(host, port));
            logger.info("Server was created");
            int ops = serverSocketChannel.validOps();
            SelectionKey key = serverSocketChannel.register(selector, ops, null);
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey myKey = keyIterator.next();
                    if (myKey.isAcceptable()) {
                        SocketChannel client = serverSocketChannel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                    } else if (myKey.isReadable()) {
                        SocketChannel client = (SocketChannel) myKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(256);
                        client.read(buffer);
                        String result = new String(buffer.array()).trim();
                        logger.info("Message from client: " + result);
                        client.close();
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException occurs");
        }
    }
}
