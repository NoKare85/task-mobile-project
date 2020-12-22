package com.example.tasksapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {

    public List<User> userResults;
    ListView myListView;
    private TextView userViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userViewResult = findViewById(R.id.text_view_id);

        myListView = (ListView) findViewById(R.id.myListView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quiet-tundra-67029.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TaskApi taskApi = retrofit.create(TaskApi.class);
        UserApi userApi = retrofit.create(UserApi.class);

        Call<List<User>> callUsers = userApi.getUsers();

        callUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    userViewResult.setText("Code: " + response.code());
                    return;
                }

                userResults = response.body();
                newUserAdapter(userResults);
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                userViewResult.setText(t.getMessage());
            }
        });

    }
    public void switchView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void newUserAdapter(List<User> results) {
        UserAdapter userAdapter = new UserAdapter(this, results);
        myListView.setAdapter(userAdapter);
    }

}
