package com.example.tasksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasksapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public List<Task> taskResults;
    ListView myTaskView;
    private TextView taskViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskViewResult = findViewById(R.id.text_view_id);

        myTaskView = (ListView) findViewById(R.id.myTaskView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quiet-tundra-67029.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TaskApi taskApi = retrofit.create(TaskApi.class);

        Call<List<Task>> callTasks = taskApi.getTasks();

        callTasks.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (!response.isSuccessful()) {
                    taskViewResult.setText("Code: " + response.code());
                    return;
                }

                taskResults = response.body();
                newTaskAdapter(taskResults);
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                taskViewResult.setText(t.getMessage());
            }
        });
    }
    public void switchView(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    public void newTaskAdapter(List<Task> results) {
        TaskAdapter taskAdapter = new TaskAdapter(this, results);
        myTaskView.setAdapter(taskAdapter);
    }
}
