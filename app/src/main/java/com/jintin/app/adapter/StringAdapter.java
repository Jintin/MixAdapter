package com.jintin.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jintin.app.R;

import java.util.List;

/**
 * basic adapter example
 */
public class StringAdapter extends RecyclerView.Adapter<StringAdapter.Holder> {

    public interface OnAdapterItemClickListener {
        void onItemClick(int position);
    }

    private List<String> items;
    private OnAdapterItemClickListener listener;

    public StringAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_string, parent, false);
        final Holder holder = new Holder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItemClickListener(OnAdapterItemClickListener listener) {
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
