package com.tipes.mobile.viewmodel_repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tipes.mobile.connection.networkapi.ClientGetRetrofit;
import com.tipes.mobile.connection.networkapi.UserEndPoint;
import com.tipes.mobile.connection.session.SharedPrefManager;
import com.tipes.mobile.model.ModelLogin;
import com.tipes.mobile.model.akun.ModelAkun;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoUser {
    private static final String TAG = RepoUser.class.getSimpleName();
    private UserEndPoint mServiceUserEP;
    private SharedPrefManager mSPM;
    private String myUsername;
    public RepoUser(Application application) {
        mServiceUserEP = ClientGetRetrofit.getClientRetrofitWithNull().create(UserEndPoint.class);
        mSPM = new SharedPrefManager(application);
        myUsername = mSPM.getSPUsername();
    }
    /**
     ==================== Proses Aksi Retrofit
     */
    public LiveData<ModelLogin> loginUser(final String username, final String password)
    {
        final MutableLiveData<ModelLogin> data = new MutableLiveData<>();
        mServiceUserEP.loginUser(username, password)
                .enqueue(new Callback<ModelLogin>() {
                    @Override
                    public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
                        if (response.isSuccessful()
                                && response.body() != null
                                && response.code() == 200)
                        {
                            data.setValue(response.body());
                            makeLogI("Status Repo Log = " + response.code());
                        } else
                        {
                            data.setValue(null);
                            makeLogI("Status Repo Log = " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelLogin> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<ModelAkun> getMyAkun()
    {
        final MutableLiveData<ModelAkun> data = new MutableLiveData<>();
        mServiceUserEP.getMyAkun(myUsername)
                .enqueue(new Callback<ModelAkun>() {
                    @Override
                    public void onResponse(Call<ModelAkun> call, Response<ModelAkun> response) {
                        if (response.isSuccessful() && response.body() != null && response.code() == 201)
                        {
                            data.setValue(response.body());
                            makeLogI("Status Repo Log = " + response.code());
                        } else
                        {
                            data.setValue(null);
                            makeLogI("Status Repo Log = " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelAkun> call, Throwable t) {
                        data.setValue(null);
                        makeLogE("Status Repo Log = onFailure" );
                    }
                });
        return data;
    }

//    LOG METHOD
    private void makeLogI(String msg)
    {
        Log.i(TAG, msg);
    }
    private void makeLogE(String msg)
    {
        Log.e(TAG, msg);
    }
    private void makeLogD(String msg)
    {
        Log.d(TAG, msg);
    }
}
