package com.tipes.mobile.model.soal.yesno;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelSoalYNList {

    @SerializedName("id_kuisioner")
    @Expose
    private String idKuisioner;


    @SerializedName("pernyataan")
    @Expose
    private String pertanyaan;

    @SerializedName("id_kategori")
    @Expose
    private String idKategori;

    @SerializedName("id_instrument")
    @Expose
    private String idInstrument;

    @SerializedName("number")
    @Expose
    private int number;

    public String getIdKuisioner() {
        return idKuisioner;
    }

    public void setIdKuisioner(String idKuisioner) {
        this.idKuisioner = idKuisioner;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getIdInstrument() {
        return idInstrument;
    }

    public void setIdInstrument(String idInstrument) {
        this.idInstrument = idInstrument;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
