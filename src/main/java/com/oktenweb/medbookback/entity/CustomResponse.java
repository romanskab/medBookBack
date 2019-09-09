package com.oktenweb.medbookback.entity;

import lombok.Data;

@Data
public class CustomResponse {
    private String msg;
    private boolean success;

    public CustomResponse(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                '}';
    }
}
