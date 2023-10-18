package com.doubleDimple.activityDemo.enums;

public enum ResultCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败");


    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
