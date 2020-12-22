package com.example.tasksapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TaskApi {

    @GET("tasks")
    Call<List<Task>> getTasks();

    @POST("tasks")
    Call<List<Task>> addTask();
}
