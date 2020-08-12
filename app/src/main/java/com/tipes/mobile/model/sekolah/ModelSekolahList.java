package com.tipes.mobile.model.sekolah;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelSekolahList {

    @SerializedName("id_sekolah")
    @Expose
    private String idSekolah;

    @SerializedName("nama_sekolah")
    @Expose
    private String namaSekolah;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    public String getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(String idSekolah) {
        this.idSekolah = idSekolah;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
