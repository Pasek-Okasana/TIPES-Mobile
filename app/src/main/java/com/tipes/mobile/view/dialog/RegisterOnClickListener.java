package com.tipes.mobile.view.dialog;

import com.tipes.mobile.model.sekolah.ModelJurusanList;
import com.tipes.mobile.model.sekolah.ModelSekolahList;

import java.util.List;

public interface RegisterOnClickListener {
    void onItemPilihSekolahClick(int position, List<ModelSekolahList> mList);
    void onItemPilihJurusan1(int position, List<ModelJurusanList> mList);
    void onItemPilihJurusan2(int position, List<ModelJurusanList> mList, boolean tidakmemilih);
    void onItemPilihJurusan3(int position, List<ModelJurusanList> mList, boolean tidakmemilih);
}
