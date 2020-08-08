package com.tipes.mobile.model;

public class MenuDashboardModel {
    String nama, keterangan;
    int gambar;

    public MenuDashboardModel(String nama, String keterangan, int gambar) {
        this.nama = nama;
        this.keterangan = keterangan;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public int getGambar() {
        return gambar;
    }
}
