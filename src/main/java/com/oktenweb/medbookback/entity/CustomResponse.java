package com.oktenweb.medbookback.entity;

import lombok.Data;

@Data
public class CustomResponse {
    private String msg;

    public CustomResponse(String msg) {
        this.msg = msg;
    }

    public CustomResponse() {
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
