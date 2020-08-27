package com.tipes.mobile.viewmodel_repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tipes.mobile.connection.networkapi.ClientGetRetrofit;
import com.tipes.mobile.connection.networkapi.QuizEndPoint;
import com.tipes.mobile.connection.session.SharedPrefManager;
import com.tipes.mobile.model.kategory.ModelKategori;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoQuiz {
    private static final String TAG = RepoQuiz.class.getSimpleName();
    private QuizEndPoint mServiceQuizEP;
    private SharedPrefManager mSPM;
    private String myUsername;
    public RepoQuiz(Application application) {
        mServiceQuizEP = ClientGetRetrofit.getClientRetrofitWithNull().create(QuizEndPoint.class);
        mSPM = new SharedPrefManager(application);
        myUsername = mSPM.getSPUsername();
    }
    /**
     ==================== Proses Aksi Retrofit
     */
    public LiveData<ModelKategori> getKategori()
    {
        final MutableLiveData<ModelKategori> data = new MutableLiveData<>();
        mServiceQuizEP.getKategori()
                .enqueue(new Callback<ModelKategori>() {
                    @Override
                    public void onResponse(Call<ModelKategori> call, Response<ModelKategori> response) {
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
                    public void onFailure(Call<ModelKategori> call, Throwable t) {
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
