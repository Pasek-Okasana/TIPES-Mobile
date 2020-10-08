package com.tipes.mobile.model.hasilaksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelHasilQuizData {
    @SerializedName("kompentensi")
    @Expose
    private String kompentensi;
    @SerializedName("lulus")
    @Expose
    private String lulus;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("deskripsi")
    @Expose
    private List<ModelHasilQuizData_Deskripsi> listDeskripsi = null;

    public String getKompentensi() {
        return kompentensi;
    }

    public void setKompentensi(String kompentensi) {
        this.kompentensi = kompentensi;
    }

    public String getLulus() {
        return lulus;
    }

    public void setLulus(String lulus) {
        this.lulus = lulus;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public List<ModelHasilQuizData_Deskripsi> getListDeskripsi() {
        return listDeskripsi;
    }

    public void setListDeskripsi(List<ModelHasilQuizData_Deskripsi> listDeskripsi) {
        this.listDeskripsi = listDeskripsi;
    }
}
