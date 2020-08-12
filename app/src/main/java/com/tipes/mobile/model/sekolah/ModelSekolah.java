package com.tipes.mobile.model.sekolah;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tipes.mobile.model.akun.ModelAkunList;

import java.util.List;

public class ModelSekolah {

    @SerializedName("message")
    @Expose
    private String msg;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("data")
    @Expose
    private List<ModelSekolahList> sekolahList;


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

    public List<ModelSekolahList> getSekolahList() {
        return sekolahList;
    }

    public void setSekolahList(List<ModelSekolahList> sekolahList) {
        this.sekolahList = sekolahList;
    }
}
