package com.tipes.mobile.view.user.kuisioner.soal;

import com.tipes.mobile.model.soal.yesno.ModelSoalYNList;

import java.util.List;

public interface SoalYNOnClickListener {
    void onItemSoal1Click(int position, int id, List<ModelSoalYNList> mList, String instrumen);
}
