package com.jintin.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        String[] items = new String[]{"Basic", "Grid", "Multi-Holder", "Dynamic"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, BasicActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, GridActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, MultiTypeActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, DynamicActivity.class));
                        break;
                }
            }
        });
    }
}
