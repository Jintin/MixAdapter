package com.jintin.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jintin.app.R;
import com.jintin.mixadapter.OnMixAdapterItemClickListener;

import java.util.List;

/**
 * basic adapter example
 */
public class StringAdapter extends RecyclerView.Adapter<StringAdapter.Holder> {

    private List<String> items;
    private RecyclerView recyclerView;
    private OnMixAdapterItemClickListener listener;

    public StringAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = null;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_string, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerView != null) {
                    int position = (int) view.getTag();
                    int parentPosition = recyclerView.getChildAdapterPosition(v);
                    if (listener != null) {
                        listener.onItemClick(position, parentPosition);
                    }
                }
            }
        });
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.textView.setText(items.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItemClickListener(OnMixAdapterItemClickListener listener) {
        this.listener = listener;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView textView;

        private Holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
