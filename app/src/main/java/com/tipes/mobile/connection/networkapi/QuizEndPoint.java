package com.tipes.mobile.connection.networkapi;

import com.tipes.mobile.model.aksiquiz.ModelAksiQuiz;
import com.tipes.mobile.model.instrumen.ModelInstrumen;
import com.tipes.mobile.model.kategory.ModelKategori;
import com.tipes.mobile.model.soal.yesno.ModelSoalYN;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuizEndPoint {

    @GET("api/kategori")
    Call<ModelKategori> getKategori();

    @GET("api/instrument")
    Call<ModelInstrumen> getInstrument();

    @GET("api/soal/{kategori}/{instrument}")
    Call<ModelSoalYN> getSoalYesNo(
            @Path("kategori") String kategori,
            @Path("instrument") String instrument
    );

    @FormUrlEncoded
    @POST("api/quis/aksi_quiz")
    Call<ModelAksiQuiz> postAksiQuiz(@FieldMap Map<String, String> parameter);

    @FormUrlEncoded
    @POST("api/quis/aksi_hasil")
    Call<ModelAksiQuiz> postAksiHasil(@FieldMap Map<String, String> parameter);
//
//
//    @FormUrlEncoded
//    @POST("user/auth/register")
//    Call<ModelAuth> registerRequest(@Field("email") String email,
//                                    @Field("username") String username,
//                                    @Field("fullname") String namalengkap,
//                                    @Field("password") String password);
//
//    @FormUrlEncoded
//    @POST("user/auth/lupapassword")
//    Call<ModelAuth> postLupaPassword(
//            @Field("email") String email
//    );
//
//    @FormUrlEncoded
//    @PUT("user/auth/updatepassword")
//    Call<ModelAuth> putUpdateLupaPassword(@Field("email") String email,
//                                          @Field("code") String code,
//                                          @Field("data") String data
//    );
//
//    @FormUrlEncoded
//    @POST("user/pasien")
//    Call<ModelData> postPasien(@Field("id_peng") String idpeng,
//                               @Field("nama_u") String username,
//                               @Field("nama_p") String namapasien,
//                               @Field("jk") String jeniskelamin,
//                               @Field("alamat") String alamat,
//                               @Field("tgl_lahir") String tgllahir,
//                               @Field("kewarganegaraan") String kewarganegaraan,
//                               @Field("no_darurat") String nodarurat,
//                               @Field("ket") String ket);
//
//    @FormUrlEncoded
//    @PUT("user/pasien")
//    Call<ModelData> putPasien(@Field("id_pasien") String idpas,
//                              @Field("id_peng") String idpeng,
//                              @Field("nama_u") String username,
//                              @Field("nama_p") String namapasien,
//                              @Field("jk") String jeniskelamin,
//                              @Field("alamat") String alamat,
//                              @Field("tgl_lahir") String tgllahir,
//                              @Field("kewarganegaraan") String kewarganegaraan,
//                              @Field("no_darurat") String nodarurat,
//                              @Field("ket") String ket);
//
//

//
//    @GET("user/pasien/nama")
//    Call<ModelPilihPasien> getNamaPasien(
//            @Query("myid") String myid
//    );
//
//    @GET("user/pasien/detail/idpasien/{id}")
//    Call<ModelPasienDetail> getDetailPasien(
//            @Path("id") String idpas
//    );
//
//
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "user/pasien", hasBody = true)
//    Call<ModelData> deletePasien(
//            @Field("id_pasien") String idpasien
//    );
//
//    @GET("user/profile/idusr/{id}")
//    Call<ModelProfile> getProfile(
//            @Path("id") String idusr
//    );
//
//    @FormUrlEncoded
//    @PUT("user/profile/edit")
//    Call<ModelData> putProfile(@Field("idusr") String idusr,
//                               @Field("field") String field,
//                               @Field("data") String data);
//
//    @Multipart
//    @POST("uploadapi/editapi")
//    Call<ModelData> uploadImageProfile(@Part MultipartBody.Part image,
//                                       @PartMap Map<String, RequestBody> text);
//
//    @FormUrlEncoded
//    @PUT("user/profile/edit")
//    Call<ModelData> putPassword(@Field("idusr") String idusr,
//                                @Field("field") String field,
//                                @Field("data") String data,
//                                @Field("oldpassword") String oldpass);

}
