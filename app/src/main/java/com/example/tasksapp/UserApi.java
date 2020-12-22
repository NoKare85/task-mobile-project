package com.example.tasksapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("users")
    Call<List<User>> getUsers();

    @POST("users")
    @FormUrlEncoded
    Call<User> addUser(@Field("firstname") String firstname,
                       @Field("lastname") String lastname);
}
