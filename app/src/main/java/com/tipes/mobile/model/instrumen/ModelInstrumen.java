package com.tipes.mobile.model.instrumen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelInstrumen {

    @SerializedName("message")
    @Expose
    private String msg;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("data")
    @Expose
    private List<ModelInstrumenList> dataListst;


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

    public List<ModelInstrumenList> getDataListst() {
        return dataListst;
    }

    public void setDataListst(List<ModelInstrumenList> dataListst) {
        this.dataListst = dataListst;
    }
}
