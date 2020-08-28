package com.tipes.mobile.model.soal.yesno;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelSoalYN {

    @SerializedName("message")
    @Expose
    private String msg;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("data")
    @Expose
    private List<ModelSoalYNList> dataListst;


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

    public List<ModelSoalYNList> getDataListst() {
        return dataListst;
    }

    public void setDataListst(List<ModelSoalYNList> dataListst) {
        this.dataListst = dataListst;
    }
}
