package com.tipes.mobile.connection.networkapi;

import com.tipes.mobile.model.ModelLogin;
import com.tipes.mobile.model.ModelMain;
import com.tipes.mobile.model.akun.ModelAkun;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserEndPoint {
    @FormUrlEncoded
    @POST("api/login")
    Call<ModelLogin> loginUser(@Field("username") String username,
                                  @Field("password") String password);

    @GET("api/siswa/show/{username}")
    Call<ModelAkun> getMyAkun(
            @Path("username") String username
    );

    @FormUrlEncoded
    @POST("api/register")
    Call<ModelMain> registrasiUser(
            @FieldMap Map<String, String> parameter
    );

//    @FormUrlEncoded
//    @POST("user/auth/activate")
//    Call<ModelAuth> activationAccount(@Field("idusr") String idusr,
//                                      @Field("code") String code);
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
