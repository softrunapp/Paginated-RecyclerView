package com.softrunapps.paginatedrecyclerviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.softrunapps.paginatedrecyclerview.PaginatedRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    List<String> data;
    PaginationAdapter paginatedAdapter;
    int conunt = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        paginatedAdapter = new PaginationAdapter();
        paginatedAdapter.setFirstPage(2);
        paginatedAdapter.setItemCount(15);
        PaginatedRecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(paginatedAdapter);
        recyclerView.setOnPaginationListener(new PaginatedRecyclerView.OnPaginationListener() {
            @Override
            public void onNextPage(int loadingPage) {
                paginatedAdapter.submitItems(getNewItems(loadingPage));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();
            }
        });
        paginatedAdapter.submitItems(getNewItems(paginatedAdapter.getFirstPage()));


    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 35; i++)
            data.add(i + " name");
    }

    private List<String> getNewItems(int page) {
        Toast.makeText(this, "Loading page: " + page, Toast.LENGTH_SHORT).show();
        List<String> news = new ArrayList<>();
        int start = page * conunt - conunt;
        int end = page * conunt;
        for (int i = start; i < end; i++) {
            if (i < data.size()) {
                news.add(data.get(i));
            }
        }
        return news;
    }
}
