package com.exam.account_service.api.account.dto;

public class StatusResponse {

    private final int transactionStatusCode;
    private final String transactionStatusDescription;

    public StatusResponse(int transactionStatusCode, String transactionStatusDescription) {
        this.transactionStatusCode = transactionStatusCode;
        this.transactionStatusDescription = transactionStatusDescription;
    }

    public int getTransactionStatusCode() {
        return transactionStatusCode;
    }

    public String getTransactionStatusDescription() {
        return transactionStatusDescription;
    }
}
