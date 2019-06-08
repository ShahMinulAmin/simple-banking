package com.mns.banking.types;

public enum TransactionType {

    DEPOSIT(1),
    WITHDRAW(2),
    TRANSFER(3);

    private final int code;

    TransactionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
