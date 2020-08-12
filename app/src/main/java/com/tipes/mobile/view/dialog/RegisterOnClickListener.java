package com.tipes.mobile.view.dialog;

import com.tipes.mobile.model.sekolah.ModelSekolahList;

import java.util.List;

public interface RegisterOnClickListener {
    void onItemPilihSekolahClick(int position, List<ModelSekolahList> mList);
//    void onItemPilihJurusan1Click(int position, List<ModelProv_ListData> mList);
//    void onItemPilihKotaClick(int position, List<ModelKota_ListData> mList);
//    void onItemPilihKecClick(int position, List<ModelKec_ListData> mList);
//    void onItemPilihDesaClick(int position, List<ModelDesa_ListData> mList);
}
