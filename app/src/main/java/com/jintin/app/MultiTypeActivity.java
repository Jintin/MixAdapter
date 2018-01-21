package com.jintin.app;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jintin.app.adapter.Color;
import com.jintin.app.adapter.ColorAdapter;
import com.jintin.app.adapter.StringAdapter;
import com.jintin.mixadapter.MixAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiTypeActivity extends BaseActivity {

    private List<String> itemsA = new ArrayList<>(Arrays.asList("A1", "A2", "A3", "A4", "A5"));
    private List<String> itemsB = new ArrayList<>(Arrays.asList("B1", "B2", "B3", "B4", "B5"));
    private List<String> itemsC = new ArrayList<>(Arrays.asList("C1", "C2", "C3", "C4", "C5"));

    private List<Color> items1 = new ArrayList<>(Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.PURPLE));
    private List<Color> items2 = new ArrayList<>(Arrays.asList(Color.GREEN, Color.PURPLE, Color.RED, Color.BLUE, Color.ORANGE));
    private List<Color> items3 = new ArrayList<>(Arrays.asList(Color.PURPLE, Color.ORANGE, Color.BLUE, Color.GREEN, Color.RED));

    @Override
    protected void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        MixAdapter<RecyclerView.ViewHolder> adapter = new MixAdapter<>();
        StringAdapter adapterA = new StringAdapter(itemsA);
        StringAdapter adapterB = new StringAdapter(itemsB);
        StringAdapter adapterC = new StringAdapter(itemsC);
        ColorAdapter adapter1 = new ColorAdapter(this, items1);
        ColorAdapter adapter2 = new ColorAdapter(this, items2);
        ColorAdapter adapter3 = new ColorAdapter(this, items3);
        adapter.addAdapter(adapterA);
        adapter.addAdapter(adapter1);
        adapter.addAdapter(adapterB);
        adapter.addAdapter(adapter2);
        adapter.addAdapter(adapterC);
        adapter.addAdapter(adapter3);
        return adapter;
    }

}
