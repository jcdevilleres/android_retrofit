package com.ceo.futurestudretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import io.futurestud.retrofit1.api.model.GitHubRepo;
import io.futurestud.retrofit1.api.service.GitHubClient;
import io.futurestud.retrofit1.ui.adapter.GitHubRepoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.pagination_list);

        //Retrofit builder with the base URL, converter for Java to JSON object
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        //Initialize the retrofit instance with the builder settings
        Retrofit retrofit = builder.build();

        //Create the GitHubClient class
        GitHubClient client = retrofit.create(GitHubClient.class);

        //Client call method to get repos list for a user
        Call<List<GitHubRepo>> call = client.reposForUser("jcdevilleres");

        //Async method for the client
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                List<GitHubRepo> repos = response.body();

                listView.setAdapter(new GitHubRepoAdapter(MainActivity.this, repos));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
