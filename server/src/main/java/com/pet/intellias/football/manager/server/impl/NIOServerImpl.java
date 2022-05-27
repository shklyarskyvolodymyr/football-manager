package com.pet.intellias.football.manager.server.impl;

import com.pet.intellias.football.manager.server.ServerRunnable;
import com.pet.intellias.football.manager.server.NIOServer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class NIOServerImpl implements NIOServer {

    private static Logger logger = Logger.getLogger(NIOServerImpl.class.getName());
    private static int MAX_THREADS = 3;
    private ServerRunnable server;

    public NIOServerImpl(String host, int port) {
        server = new ServerRunnable(host, port);
    }

    @Override
    public void createServer() {
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);
        pool.execute(server);
        pool.shutdown();
    }
}
