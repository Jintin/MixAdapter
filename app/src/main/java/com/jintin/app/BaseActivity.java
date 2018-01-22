package com.jintin.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        setupRecyclerView(recyclerView);
        recyclerView.setAdapter(getAdapter());
    }

    protected abstract void setupRecyclerView(RecyclerView recyclerView);

    protected abstract RecyclerView.Adapter getAdapter();
}
