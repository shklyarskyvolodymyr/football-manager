package com.pet.intellias.football.manager.server.domain;

import java.util.HashMap;
import java.util.Map;

public enum ServerOperationValues {

    ACCEPTABLE(1 << 4),
    WRITABLE(1 << 2),
    READABLE(1 << 0);

    private final int value;
    private static Map map = new HashMap();

    ServerOperationValues(int value) {
        this.value = value;
    }

    static {
        for (ServerOperationValues value : ServerOperationValues.values()) {
            map.put(value.getValue(), value);
        }
    }

    public static ServerOperationValues valueOf(int readyOps) {
        return (ServerOperationValues) map.get(readyOps);
    }

    private int getValue() {
        return value;
    }
}
