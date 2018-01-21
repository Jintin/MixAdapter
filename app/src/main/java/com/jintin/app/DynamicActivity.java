package com.jintin.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jintin.app.adapter.StringAdapter;
import com.jintin.mixadapter.MixAdapter;
import com.jintin.mixadapter.OnMixAdapterItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicActivity extends BaseActivity {

    private List<String> itemsA = new ArrayList<>(Arrays.asList("A1", "A2", "A3", "A4", "A5"));
    private List<String> itemsB = new ArrayList<>(Arrays.asList("B1", "B2", "B3", "B4", "B5"));
    private List<String> itemsC = new ArrayList<>(Arrays.asList("C1", "C2", "C3", "C4", "C5"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Click to Add/Remove", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        final MixAdapter<StringAdapter.Holder> adapter = new MixAdapter<>();
        StringAdapter adapterA = new StringAdapter(itemsA);
        setListener(itemsA, adapterA, adapter);
        StringAdapter adapterB = new StringAdapter(itemsB);
        setListener(itemsB, adapterB, adapter);
        StringAdapter adapterC = new StringAdapter(itemsC);
        setListener(itemsC, adapterC, adapter);
        adapter.addAdapter(adapterA);
        adapter.addAdapter(adapterB);
        adapter.addAdapter(adapterC);
        return adapter;
    }

    private void setListener(final List<String> items, final StringAdapter adapter, final MixAdapter parentAdapter) {
        adapter.setItemClickListener(new OnMixAdapterItemClickListener() {
            @Override
            public void onItemClick(final int position, final int parentPosition) {
                showDialog(position, parentPosition, new OnItemActionListener() {
                    @Override
                    public void onDelete() {
                        items.remove(position);
                        parentAdapter.notifyItemRemoved(parentPosition);
                    }

                    @Override
                    public void onClone() {
                        String string = items.get(position);
                        items.add(position + 1, string);
                        parentAdapter.notifyItemInserted(parentPosition + 1);
                    }
                });
            }
        });
    }

}
