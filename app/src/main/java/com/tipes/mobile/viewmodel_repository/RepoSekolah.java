package com.tipes.mobile.viewmodel_repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tipes.mobile.connection.networkapi.ClientGetRetrofit;
import com.tipes.mobile.connection.networkapi.SekolahEndPoint;
import com.tipes.mobile.connection.session.SharedPrefManager;
import com.tipes.mobile.model.sekolah.ModelSekolah;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoSekolah {
    private static final String TAG = RepoSekolah.class.getSimpleName();
    private SekolahEndPoint mServiceUserEP;
    private SharedPrefManager mSPM;
    private String myUsername;
    public RepoSekolah(Application application) {
        mServiceUserEP = ClientGetRetrofit.getClientRetrofitWithNull().create(SekolahEndPoint.class);
        mSPM = new SharedPrefManager(application);
        myUsername = mSPM.getSPUsername();
    }
    /**
     ==================== Proses Aksi Retrofit
     */

    public LiveData<ModelSekolah> getSekolah()
    {
        final MutableLiveData<ModelSekolah> data = new MutableLiveData<>();
        mServiceUserEP.getSekolah()
                .enqueue(new Callback<ModelSekolah>() {
                    @Override
                    public void onResponse(Call<ModelSekolah> call, Response<ModelSekolah> response) {
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
                    public void onFailure(Call<ModelSekolah> call, Throwable t) {
                        data.setValue(null);
                        makeLogI("Status Repo Log = OnFailure" );
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
