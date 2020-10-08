package com.tipes.mobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tipes.mobile.model.aksiquiz.ModelAksiQuiz;
import com.tipes.mobile.model.hasilaksi.ModelHasilQuiz;
import com.tipes.mobile.model.instrumen.ModelInstrumen;
import com.tipes.mobile.model.kategory.ModelKategori;
import com.tipes.mobile.model.soal.yesno.ModelSoalYN;
import com.tipes.mobile.viewmodel_repository.RepoQuiz;

import java.util.Map;

public class ViMoQuiz extends AndroidViewModel {
    private RepoQuiz mRepo;
    private LiveData<ModelKategori> livedataKategori;
    private LiveData<ModelInstrumen> liveDataInstrumen;
    private LiveData<ModelSoalYN> liveDataSoalYN;
    private LiveData<ModelAksiQuiz> liveDataAksiQuiz;
    private LiveData<ModelHasilQuiz> liveDataHasilQuiz;
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
    public LiveData<ModelHasilQuiz> getHasilAksi(String idsekolah)
    {
        liveDataHasilQuiz = mRepo.getHasilQuiz(idsekolah);
        return liveDataHasilQuiz;
    }

    public LiveData<ModelAksiQuiz> postAksiQuiz(Map<String, String> parameter) {
        liveDataAksiQuiz = mRepo.postAksiQuiz(parameter);
        return liveDataAksiQuiz;
    }
    public LiveData<ModelAksiQuiz> postAksiHasil(Map<String, String> parameter) {
        liveDataAksiQuiz = mRepo.postAksiHasil(parameter);
        return liveDataAksiQuiz;
    }
}
