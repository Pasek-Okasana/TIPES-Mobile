package com.tipes.mobile.model.sekolah;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelJurusanList {

    @SerializedName("id_jurusan")
    @Expose
    private String idJurusan;

    @SerializedName("nama_jurusan")
    @Expose
    private String namaJurusan;

    @SerializedName("id_sekolah")
    @Expose
    private String idSekolah;;

    public String getIdJurusan() {
        return idJurusan;
    }

    public void setIdJurusan(String idJurusan) {
        this.idJurusan = idJurusan;
    }

    public String getNamaJurusan() {
        return namaJurusan;
    }

    public void setNamaJurusan(String namaJurusan) {
        this.namaJurusan = namaJurusan;
    }

    public String getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(String idSekolah) {
        this.idSekolah = idSekolah;
    }
}
