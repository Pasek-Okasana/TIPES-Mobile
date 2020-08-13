package com.tipes.mobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tipes.mobile.model.akun.ModelAkun;
import com.tipes.mobile.model.sekolah.ModelJurusan;
import com.tipes.mobile.model.sekolah.ModelSekolah;
import com.tipes.mobile.viewmodel_repository.RepoSekolah;

public class ViMoSekolah extends AndroidViewModel {
    private RepoSekolah mRepo;
    private LiveData<ModelSekolah> livedataSekolah;
    private LiveData<ModelJurusan> livedataJurusan;
    private LiveData<ModelAkun> livedataAkun;
    public ViMoSekolah(@NonNull Application application) {
        super(application);
        mRepo = new RepoSekolah(application);
    }

    public LiveData<ModelSekolah> getSekolah()
    {
        livedataSekolah = mRepo.getSekolah();
        return livedataSekolah;
    }
    public LiveData<ModelJurusan> getJurusan()
    {
        livedataJurusan = mRepo.getJurusan();
        return livedataJurusan;
    }
}
