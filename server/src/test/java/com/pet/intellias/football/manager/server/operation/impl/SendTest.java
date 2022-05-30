package com.pet.intellias.football.manager.server.operation.impl;

import com.pet.intellias.football.manager.server.domain.Response;
import com.pet.intellias.football.manager.server.operation.ServerCommand;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import static org.junit.jupiter.api.Assertions.*;

class SendTest {

    @Mock
    private SelectionKey key;

    @Mock
    private Selector selector;

    @InjectMocks
    private ServerCommand unit;

//    @Test
//    void runCommand() throws IOException {
//        key = Mockito.mock(SelectionKey.class);
//        Mockito.when(key.channel()).thenReturn(SocketChannel.open());
//
//        unit = new Send(key, selector, "test");
//
//        Response response = unit.runCommand();
//        assertEquals(4, response.getBytes());
//    }
}