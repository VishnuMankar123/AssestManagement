package com.inventory.assetmanagement.enumeration;

public enum Status {
    LIVE(1),
    STANDBY(2),
    REVOKED(3);

    final int code;

    Status(int code) {
        this.code = code;
    }
}
