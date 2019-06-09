package com.mns.banking.types;

public enum TransactionType {

    DEPOSIT(1), // TODO: should be used in deposit operation
    WITHDRAW(2), // TODO: should be used in withdraw operation
    TRANSFER(3);

    private final int code;

    TransactionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
