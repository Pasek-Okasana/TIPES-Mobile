package com.tipes.mobile.model.hasilaksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelHasilQuiz {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private ModelHasilQuizData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ModelHasilQuizData getData() {
        return data;
    }

    public void setData(ModelHasilQuizData data) {
        this.data = data;
    }
}

