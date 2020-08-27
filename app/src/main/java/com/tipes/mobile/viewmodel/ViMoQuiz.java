package com.tipes.mobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tipes.mobile.model.kategory.ModelKategori;
import com.tipes.mobile.viewmodel_repository.RepoQuiz;

public class ViMoQuiz extends AndroidViewModel {
    private RepoQuiz mRepo;
    private LiveData<ModelKategori> livedataKategori;
    public ViMoQuiz(@NonNull Application application) {
        super(application);
        mRepo = new RepoQuiz(application);
    }

    public LiveData<ModelKategori> getKategori()
    {
        livedataKategori = mRepo.getKategori();
        return livedataKategori;
    }

}
