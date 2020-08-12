package com.tipes.mobile.model.akun;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelAkun {

    @SerializedName("message")
    @Expose
    private String msg;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("data")
    @Expose
    private List<ModelAkunList> akunList;


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

    public List<ModelAkunList> getAkunList() {
        return akunList;
    }

    public void setAkunList(List<ModelAkunList> akunList) {
        this.akunList = akunList;
    }
}
