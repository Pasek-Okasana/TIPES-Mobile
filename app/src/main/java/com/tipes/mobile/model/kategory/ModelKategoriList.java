package com.tipes.mobile.model.kategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelKategoriList {

    @SerializedName("id_kategori")
    @Expose
    private String idKategori;


    @SerializedName("nama_kategori")
    @Expose
    private String namaKategori;


    @SerializedName("waktu")
    @Expose
    private String waktu;

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
