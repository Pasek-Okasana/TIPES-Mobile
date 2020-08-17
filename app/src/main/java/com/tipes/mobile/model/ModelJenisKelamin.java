package com.tipes.mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelJenisKelamin {
    @SerializedName("idjk")
    @Expose
    private String idJk;

    @SerializedName("jk")
    @Expose
    private String jk;

    public ModelJenisKelamin(String idJk, String jk) {
        this.idJk = idJk;
        this.jk = jk;
    }

    public String getIdJk() {
        return idJk;
    }

    public String getJk() {
        return jk;
    }
}
