package com.inventory.assetmanagement.enumeration;

public enum AssetType {
    LAPTOP(10),
    HUB(11),
    MONITOR(12);

    final int code;

    AssetType(int code) {
        this.code = code;
    }
}
