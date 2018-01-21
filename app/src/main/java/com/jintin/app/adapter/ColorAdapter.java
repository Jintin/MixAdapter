package com.jintin.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jintin.app.R;

import java.util.List;

/**
 * multiple view holder type example
 */
public class ColorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Color> items;

    public ColorAdapter(Context context, List<Color> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3 + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                final View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_color, parent, false);
                return new Holder1(view1);
            case 2:
                final View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_color2, parent, false);
                return new Holder2(view2);
            case 3:
                final View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_color3, parent, false);
                return new Holder3(view3);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int color = context.getResources().getColor(items.get(position).getColor());
        switch (getItemViewType(position)) {
            case 1:
                Holder1 holder1 = (Holder1) holder;
                holder1.colorView.setBackgroundColor(color);
                break;
            case 2:
                Holder2 holder2 = (Holder2) holder;
                holder2.colorView.setBackgroundColor(color);
                holder2.colorView2.setBackgroundColor(color);
                break;
            case 3:
                Holder3 holder3 = (Holder3) holder;
                holder3.colorView.setBackgroundColor(color);
                holder3.colorView2.setBackgroundColor(color);
                holder3.colorView3.setBackgroundColor(color);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Holder1 extends RecyclerView.ViewHolder {
        View colorView;

        private Holder1(View itemView) {
            super(itemView);
            colorView = itemView.findViewById(R.id.color);
        }
    }

    public static class Holder2 extends RecyclerView.ViewHolder {
        View colorView;
        View colorView2;

        private Holder2(View itemView) {
            super(itemView);
            colorView = itemView.findViewById(R.id.color);
            colorView2 = itemView.findViewById(R.id.color2);
        }
    }

    public static class Holder3 extends RecyclerView.ViewHolder {
        View colorView;
        View colorView2;
        View colorView3;

        private Holder3(View itemView) {
            super(itemView);
            colorView = itemView.findViewById(R.id.color);
            colorView2 = itemView.findViewById(R.id.color2);
            colorView3 = itemView.findViewById(R.id.color3);
        }
    }
}
