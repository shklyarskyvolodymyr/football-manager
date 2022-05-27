package com.pet.intellias.football.manager.client.domain;

public enum ClientOperationValues {

    SEND("SEND"),
    RECEIVE("RECEIVE");

    private final String operation;

    ClientOperationValues(String operation) {
        this.operation = operation;
    }
}
