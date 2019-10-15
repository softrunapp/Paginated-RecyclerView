package com.softrunapps.paginatedrecyclerviewapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.softrunapps.paginatedrecyclerview.PaginatedAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<User> data;
    MyAdapter adapter;
    int conunter = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        adapter = new MyAdapter();
        adapter.setDefaultRecyclerView(this, R.id.recyclerView);
        adapter.setOnPaginationListener(new PaginatedAdapter.OnPaginationListener() {
            @Override
            public void onCurrentPage(int page) {
                Toast.makeText(MainActivity.this, "Page " + page + " loaded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNextPage(int page) {
                getNewItems(page);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();
            }
        });


        getNewItems(adapter.getStartPage());
    }


    public void onGetDate(List<User> users) {
        adapter.submitItems(users);
    }

    private void getNewItems(final int page) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<User> users = new ArrayList<>();
                int start = page * conunter - conunter;
                int end = page * conunter;
                for (int i = start; i < end; i++) {
                    if (i < data.size()) {
                        users.add(data.get(i));
                    }
                }
                onGetDate(users);
            }
        }, 3000);
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 55; i++)
            data.add(new User(i, "username " + i, "user" + i + "@mail.com"));
    }

}
