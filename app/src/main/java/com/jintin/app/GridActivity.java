package com.jintin.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jintin.app.adapter.StringAdapter;
import com.jintin.mixadapter.MixAdapter;

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
        setListener(adapter, adapterA);
        StringAdapter adapterB = new StringAdapter(itemsB);
        setListener(adapter, adapterB);
        StringAdapter adapterC = new StringAdapter(itemsC);
        setListener(adapter, adapterC);
        adapter.addAdapter(adapterA);
        adapter.addAdapter(adapterB);
        adapter.addAdapter(adapterC);
        return adapter;
    }

    private void setListener(final MixAdapter mixAdapter, final StringAdapter adapter) {
        adapter.setItemClickListener(new StringAdapter.OnAdapterItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int childPosition = position - mixAdapter.getAdapterOffset(adapter);
                Toast.makeText(GridActivity.this, "child: " + childPosition + ", global: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
