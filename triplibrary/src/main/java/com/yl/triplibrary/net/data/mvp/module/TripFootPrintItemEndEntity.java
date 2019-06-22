package com.yl.triplibrary.net.data.mvp.module;

public class TripFootPrintItemEndEntity {
     private String store_count;
    private String address_info;
    private String time;

    public TripFootPrintItemEndEntity(String store_count, String address_info, String time) {
        this.store_count = store_count;
        this.address_info = address_info;
        this.time = time;
    }

    public String getStore_count() {
        return store_count;
    }

    public void setStore_count(String store_count) {
        this.store_count = store_count;
    }

    public String getAddress_info() {
        return address_info;
    }

    public void setAddress_info(String address_info) {
        this.address_info = address_info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
