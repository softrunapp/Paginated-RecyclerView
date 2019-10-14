package com.softrunapps.paginatedrecyclerviewapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.softrunapps.paginatedrecyclerview.PaginatedAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    List<User> data;
    PaginationAdapter adapter;
    int conunt = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        adapter = new PaginationAdapter();
        adapter.setDefaultRecyclerView(this,R.id.recyclerView);
        adapter.setOnPaginationListener(new PaginatedAdapter.OnPaginationListener() {
            @Override
            public void onCurrentPage(int page) {
                Toast.makeText(MainActivity.this, "Page " + page + " loaded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNextPage(int page) {
                adapter.submitItems(getNewItems(page));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();
            }
        });


        adapter.submitItems(getNewItems(adapter.getStartPage()));
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 55; i++)
            data.add(new User(i,"username " + i, "user"+i+"@mail.com"));
    }

    private List<User> getNewItems(int page) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },2000);
        List<User> users = new ArrayList<>();
        int start = page * conunt - conunt;
        int end = page * conunt;
        for (int i = start; i < end; i++) {
            if (i < data.size()) {
                users.add(data.get(i));
            }
        }
        return users;
    }


}
