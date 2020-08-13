package com.tipes.mobile.model.sekolah;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelJurusan {

    @SerializedName("message")
    @Expose
    private String msg;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("data")
    @Expose
    private List<ModelJurusanList> jurusanList;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ModelJurusanList> getJurusanList() {
        return jurusanList;
    }

    public void setJurusanList(List<ModelJurusanList> jurusanList) {
        this.jurusanList = jurusanList;
    }
}
