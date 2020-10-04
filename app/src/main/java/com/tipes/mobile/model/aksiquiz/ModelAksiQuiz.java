package com.tipes.mobile.model.aksiquiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelAksiQuiz {
    @SerializedName("message")
    @Expose
    private String msg;

    @SerializedName("code")
    @Expose
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

