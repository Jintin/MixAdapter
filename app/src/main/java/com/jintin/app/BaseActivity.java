package com.jintin.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public abstract class BaseActivity extends AppCompatActivity {

    public interface OnItemActionListener {
        void onDelete();

        void onClone();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        setupRecyclerView(recyclerView);
        recyclerView.setAdapter(getAdapter());
    }

    protected abstract void setupRecyclerView(RecyclerView recyclerView);

    protected abstract RecyclerView.Adapter getAdapter();

    protected void showDialog(int parentPosition, final int position, final OnItemActionListener listener) {
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
