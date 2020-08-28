package com.tipes.mobile.model.instrumen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelInstrumenList {

    @SerializedName("id_instrument")
    @Expose
    private String idInstrument;


    @SerializedName("instrument")
    @Expose
    private String namaInstrument;

    public String getIdInstrument() {
        return idInstrument;
    }

    public void setIdInstrument(String idInstrument) {
        this.idInstrument = idInstrument;
    }

    public String getNamaInstrument() {
        return namaInstrument;
    }

    public void setNamaInstrument(String namaInstrument) {
        this.namaInstrument = namaInstrument;
    }
}
