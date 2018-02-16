package com.jintin.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

/**
 * Project root
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.listView)
        val items = arrayOf("Basic", "Grid", "Multi-Holder", "Dynamic")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this@MainActivity, BasicActivity::class.java))
                1 -> startActivity(Intent(this@MainActivity, GridActivity::class.java))
                2 -> startActivity(Intent(this@MainActivity, MultiTypeActivity::class.java))
                3 -> startActivity(Intent(this@MainActivity, DynamicActivity::class.java))
            }
        }
    }
}
