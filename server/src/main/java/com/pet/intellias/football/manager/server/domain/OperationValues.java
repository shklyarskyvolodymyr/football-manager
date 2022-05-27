package com.pet.intellias.football.manager.server.domain;

import java.util.HashMap;
import java.util.Map;

public enum OperationValues {

    ACCEPTABLE(1 << 4),
    WRITABLE(1 << 2),
    READABLE(1 << 0);

    private final int value;
    private static Map map = new HashMap();

    OperationValues(int value) {
        this.value = value;
    }

    static {
        for (OperationValues value : OperationValues.values()) {
            map.put(value.getValue(), value);
        }
    }

    public static OperationValues valueOf(int readyOps) {
        return (OperationValues) map.get(readyOps);
    }

    private int getValue() {
        return value;
    }
}
