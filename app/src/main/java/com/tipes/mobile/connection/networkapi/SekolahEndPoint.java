package com.tipes.mobile.connection.networkapi;

import com.tipes.mobile.model.sekolah.ModelJurusan;
import com.tipes.mobile.model.sekolah.ModelSekolah;
import com.tipes.mobile.model.sekolah.ModelShowJurusan;
import com.tipes.mobile.model.sekolah.ModelShowSekolah;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SekolahEndPoint {
    @GET("api/sekolah")
    Call<ModelSekolah> getSekolah();

    @GET("api/sekolah/show/{id}")
    Call<ModelShowSekolah> getShowSekolah(
            @Path("id") String id
    );

    @GET("api/jurusan")
    Call<ModelJurusan> getJurusan();

    @GET("api/jurusan/show/{id}")
    Call<ModelShowJurusan> getShowJurusan(
            @Path("id") String id
    );
}
