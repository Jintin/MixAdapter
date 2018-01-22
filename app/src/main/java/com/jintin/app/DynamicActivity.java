package com.jintin.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jintin.app.adapter.StringAdapter;
import com.jintin.mixadapter.MixAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicActivity extends BaseActivity {

    private List<String> itemsA = new ArrayList<>(Arrays.asList("A1", "A2", "A3", "A4", "A5"));
    private List<String> itemsB = new ArrayList<>(Arrays.asList("B1", "B2", "B3", "B4", "B5"));
    private List<String> itemsC = new ArrayList<>(Arrays.asList("C1", "C2", "C3", "C4", "C5"));

    public interface OnItemActionListener {
        void onDelete();

        void onClone();
    }

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

    private void setListener(final List<String> items, final StringAdapter adapter, final MixAdapter mixAdapter) {
        adapter.setItemClickListener(new StringAdapter.OnAdapterItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                final int childPosition = position - mixAdapter.getAdapterOffset(adapter);
                showDialog(new OnItemActionListener() {
                    @Override
                    public void onDelete() {
                        items.remove(childPosition);
                        mixAdapter.notifyItemRemoved(position);
                    }

                    @Override
                    public void onClone() {
                        String string = items.get(childPosition);
                        items.add(childPosition + 1, string);
                        mixAdapter.notifyItemInserted(position + 1);
                    }
                });
            }
        });
    }

    private void showDialog(final OnItemActionListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select action");
        builder.setItems(new String[]{"Delete", "Clone"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        listener.onDelete();
                        break;
                    case 1:
                        listener.onClone();
                        break;
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
