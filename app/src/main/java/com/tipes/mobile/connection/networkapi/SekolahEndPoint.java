package com.tipes.mobile.connection.networkapi;

import com.tipes.mobile.model.sekolah.ModelSekolah;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SekolahEndPoint {
    @GET("api/sekolah")
    Call<ModelSekolah> getSekolah();
}
