package com.jintin.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jintin.app.adapter.StringAdapter;
import com.jintin.mixadapter.MixAdapter;
import com.jintin.mixadapter.OnMixAdapterItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridActivity extends BaseActivity {

    private List<String> itemsA = new ArrayList<>(Arrays.asList("A1", "A2", "A3", "A4", "A5"));
    private List<String> itemsB = new ArrayList<>(Arrays.asList("B1", "B2", "B3", "B4", "B5"));
    private List<String> itemsC = new ArrayList<>(Arrays.asList("C1", "C2", "C3", "C4", "C5"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Click to get Index", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void setupRecyclerView(RecyclerView recyclerView) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        MixAdapter<StringAdapter.Holder> adapter = new MixAdapter<>();
        StringAdapter adapterA = new StringAdapter(itemsA);
        setListener(adapterA);
        StringAdapter adapterB = new StringAdapter(itemsB);
        setListener(adapterB);
        StringAdapter adapterC = new StringAdapter(itemsC);
        setListener(adapterC);
        adapter.addAdapter(adapterA);
        adapter.addAdapter(adapterB);
        adapter.addAdapter(adapterC);
        return adapter;
    }

    private void setListener(StringAdapter adapter) {
        adapter.setItemClickListener(new OnMixAdapterItemClickListener() {
            @Override
            public void onItemClick(int position, int parentPosition) {
                Toast.makeText(GridActivity.this, "child: " + position + ", global: " + parentPosition, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
