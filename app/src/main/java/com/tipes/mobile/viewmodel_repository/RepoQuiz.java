package com.tipes.mobile.viewmodel_repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tipes.mobile.connection.networkapi.ClientGetRetrofit;
import com.tipes.mobile.connection.networkapi.QuizEndPoint;
import com.tipes.mobile.connection.session.SharedPrefManager;
import com.tipes.mobile.model.aksiquiz.ModelAksiQuiz;
import com.tipes.mobile.model.instrumen.ModelInstrumen;
import com.tipes.mobile.model.kategory.ModelKategori;
import com.tipes.mobile.model.soal.yesno.ModelSoalYN;

import java.util.Map;

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
                            makeLogI("Status Repo Log Total = " + response.body().getDataListst().size());
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
    public LiveData<ModelInstrumen> getInstrument()
    {
        final MutableLiveData<ModelInstrumen> data = new MutableLiveData<>();
        mServiceQuizEP.getInstrument()
                .enqueue(new Callback<ModelInstrumen>() {
                    @Override
                    public void onResponse(Call<ModelInstrumen> call, Response<ModelInstrumen> response) {
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
                    public void onFailure(Call<ModelInstrumen> call, Throwable t) {
                        data.setValue(null);
                        makeLogE("Status Repo Log = onFailure" );
                    }
                });
        return data;
    }
    public LiveData<ModelSoalYN> getSoalYesNo(String kategori, String instrumen)
    {
        final MutableLiveData<ModelSoalYN> data = new MutableLiveData<>();
        mServiceQuizEP.getSoalYesNo(kategori, instrumen)
                .enqueue(new Callback<ModelSoalYN>() {
                    @Override
                    public void onResponse(Call<ModelSoalYN> call, Response<ModelSoalYN> response) {
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
                    public void onFailure(Call<ModelSoalYN> call, Throwable t) {
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

    public LiveData<ModelAksiQuiz> postAksiQuiz(Map<String, String> parameter) {
        final MutableLiveData<ModelAksiQuiz> data = new MutableLiveData<>();
        mServiceQuizEP.postAksiQuiz(parameter).enqueue(new Callback<ModelAksiQuiz>() {
            @Override
            public void onResponse(Call<ModelAksiQuiz> call, Response<ModelAksiQuiz> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 201)
                {
                    data.setValue(response.body());
                    makeLogI("Status Repo Log = " + response.code());
                } else
                {
                    ModelAksiQuiz maq = new ModelAksiQuiz();
                    maq.setCode(String.valueOf(response.code()));
                    maq.setMsg("Gagal Menyimpan data...");

                    data.setValue(maq);
                    makeLogI("Status Repo Log = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ModelAksiQuiz> call, Throwable t) {
                data.setValue(null);
                makeLogE("Status Repo Log = onFailure" );
            }
        });

        return data;
    }
}
