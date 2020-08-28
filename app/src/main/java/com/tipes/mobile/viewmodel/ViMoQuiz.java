package com.tipes.mobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tipes.mobile.model.instrumen.ModelInstrumen;
import com.tipes.mobile.model.kategory.ModelKategori;
import com.tipes.mobile.model.soal.yesno.ModelSoalYN;
import com.tipes.mobile.viewmodel_repository.RepoQuiz;

public class ViMoQuiz extends AndroidViewModel {
    private RepoQuiz mRepo;
    private LiveData<ModelKategori> livedataKategori;
    private LiveData<ModelInstrumen> liveDataInstrumen;
    private LiveData<ModelSoalYN> liveDataSoalYN;
    public ViMoQuiz(@NonNull Application application) {
        super(application);
        mRepo = new RepoQuiz(application);
    }

    public LiveData<ModelKategori> getKategori()
    {
        livedataKategori = mRepo.getKategori();
        return livedataKategori;
    }

    public LiveData<ModelInstrumen> getInstrumen()
    {
        liveDataInstrumen = mRepo.getInstrument();
        return liveDataInstrumen;
    }

    public LiveData<ModelSoalYN> getSoalYesNo(String kategori, String instrument)
    {
        liveDataSoalYN = mRepo.getSoalYesNo(kategori, instrument);
        return liveDataSoalYN;
    }
}
