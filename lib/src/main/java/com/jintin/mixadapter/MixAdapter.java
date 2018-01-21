package com.jintin.mixadapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MixAdapter<T extends RecyclerView.ViewHolder> extends Adapter<T> {
    private static final int VIEW_TYPE_OFFSET = 10000;
    private final List<Adapter<? extends T>> adapters;

    public MixAdapter() {
        this.adapters = new ArrayList<>();
    }

    public MixAdapter(final List<Adapter<? extends T>> adapters) {
        this.adapters = adapters;
    }

    public void addAdapter(Adapter<? extends T> adapter) {
        adapters.add(adapter);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        for (Adapter<? extends T> adapter : adapters) {
            adapter.onAttachedToRecyclerView(recyclerView);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        for (Adapter<? extends T> adapter : adapters) {
            adapter.onDetachedFromRecyclerView(recyclerView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int offset = 0;
        for (int i = 0; i < adapters.size(); i++) {
            Adapter<? extends T> adapter = adapters.get(i);
            if (position < offset + adapter.getItemCount()) {
                return VIEW_TYPE_OFFSET * i + adapter.getItemViewType(position - offset);
            } else {
                offset += adapter.getItemCount();
            }
        }
        throw new RuntimeException("getItemViewType size exceed");
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        int index = viewType / VIEW_TYPE_OFFSET;
        int position = viewType % VIEW_TYPE_OFFSET;
        Adapter<? extends T> adapter = adapters.get(index);
        return adapter.onCreateViewHolder(parent, position);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        int offset = 0;
        for (int i = 0; i < adapters.size(); i++) {
            Adapter<T> adapter = (Adapter<T>) adapters.get(i);
            if (position < offset + adapter.getItemCount()) {
                adapter.onBindViewHolder(holder, position - offset);
                return;
            } else {
                offset += adapter.getItemCount();
            }
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        for (Adapter<? extends T> adapter : adapters) {
            size += adapter.getItemCount();
        }
        return size;
    }
}
