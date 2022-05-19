package com.pet.intellias.football.manager.server.impl;

import com.pet.intellias.football.manager.server.NIOServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class NIOServerImpl implements NIOServer {

    private static Logger logger = Logger.getLogger(NIOServerImpl.class.getName());
//    todo use map or allocate buffer every time?
    private static Map<SocketChannel, ByteBuffer> sockets = new ConcurrentHashMap<>();
    private static String message = "Hi";
    private InetSocketAddress address;
    private Selector selector;

    public NIOServerImpl(InetSocketAddress address) {
        this.address = address;
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bind() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(address);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("server was created");
            while (true) {
                selector.select();
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();
                    handleSelection(key, message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSelection(SelectionKey key, String message) throws IOException {
        if (key.isAcceptable()) {
            accept(key);
        }
        if (key.isReadable()) {
            receive(key);
        }
        if (key.isWritable()) {
            send(key, message);
        }
    }

    @Override
    public void send(SelectionKey key, String message) throws IOException {
        logger.info("sending data to client");
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        channel.write(buffer);
        logger.info("Data was send to server: " + message);
        key.interestOps(SelectionKey.OP_READ);
        channel.close();
    }

    @Override
    public void accept(SelectionKey key) throws IOException {
        logger.info("client was connected");
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverSocketChannel.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
    }

    @Override
    public void receive(SelectionKey key) throws IOException {
        logger.info("Starting reading data from client");
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        int bytes = channel.read(buffer);
        logger.info("Read bytes from client: " + bytes);
        String result = new String(buffer.array()).trim();
        logger.info("Received from client: " + result);
        key.interestOps(SelectionKey.OP_WRITE);
    }
}
