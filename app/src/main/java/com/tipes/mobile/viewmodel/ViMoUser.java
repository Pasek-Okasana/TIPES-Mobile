package com.tipes.mobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tipes.mobile.model.ModelLogin;
import com.tipes.mobile.model.akun.ModelAkun;
import com.tipes.mobile.viewmodel_repository.RepoUser;

public class ViMoUser extends AndroidViewModel {
    private RepoUser mRepo;
    private LiveData<ModelLogin> livedataLogin;
    private LiveData<ModelAkun> livedataAkun;
    public ViMoUser(@NonNull Application application) {
        super(application);
        mRepo = new RepoUser(application);
    }

    public LiveData<ModelLogin> loginUser(String username, String password)
    {
        livedataLogin = mRepo.loginUser(username, password);
        return livedataLogin;
    }
    public LiveData<ModelAkun> getMyAkun()
    {
        livedataAkun = mRepo.getMyAkun();
        return livedataAkun;
    }
}
