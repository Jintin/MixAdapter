package com.jintin.app;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jintin.app.adapter.StringAdapter;
import com.jintin.mixadapter.MixAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicActivity extends BaseActivity {

    private List<String> itemsA = new ArrayList<>(Arrays.asList("A1", "A2", "A3", "A4", "A5"));
    private List<String> itemsB = new ArrayList<>(Arrays.asList("B1", "B2", "B3", "B4", "B5"));
    private List<String> itemsC = new ArrayList<>(Arrays.asList("C1", "C2", "C3", "C4", "C5"));

    @Override
    protected void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        MixAdapter<StringAdapter.Holder> adapter = new MixAdapter<>();
        StringAdapter adapterA = new StringAdapter(itemsA);
        StringAdapter adapterB = new StringAdapter(itemsB);
        StringAdapter adapterC = new StringAdapter(itemsC);
        adapter.addAdapter(adapterA);
        adapter.addAdapter(adapterB);
        adapter.addAdapter(adapterC);
        return adapter;
    }
}
