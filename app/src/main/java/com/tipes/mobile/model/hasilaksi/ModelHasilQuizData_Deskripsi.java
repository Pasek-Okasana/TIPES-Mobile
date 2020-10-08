package com.tipes.mobile.model.hasilaksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelHasilQuizData_Deskripsi {
    @SerializedName("tipe")
    @Expose
    private String tipe;

    @SerializedName("kepribadian")
    @Expose
    private String kepribadian;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getKepribadian() {
        return kepribadian;
    }

    public void setKepribadian(String kepribadian) {
        this.kepribadian = kepribadian;
    }
}
