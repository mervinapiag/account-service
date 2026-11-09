package com.exam.account_service.api.account.entity;

public enum AccountType {
    SAVINGS("S", "Savings"),
    CHECKING("C", "Checking");

    private final String code;
    private final String description;

    AccountType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static AccountType fromCode(String code) {
        if (code != null) {
            for (AccountType type : values()) {
                if (type.code.equalsIgnoreCase(code.trim())) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("Unknown account type code: " + code);
    }
}
