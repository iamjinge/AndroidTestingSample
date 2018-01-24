package com.jinge.helloworld;

/**
 * A Java class.
 */

public class MyJavaClass {
    public static final int CODE_CACHED = 1;
    public static final int CODE_OK = 0;
    public static final int CODE_UNKNOW = -1;

    private int errorCode;
    private String msg;

    public MyJavaClass(int errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccessful() {
        return errorCode >= CODE_OK;
    }

    public static boolean isMsgEmpty(String msg) {
        return msg == null || msg.equals("");
    }
}
