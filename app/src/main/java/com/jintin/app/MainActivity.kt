package com.jintin.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlin.reflect.KClass

/**
 * Project root
 */
class MainActivity : AppCompatActivity() {

    /**
     * Item to show in List
     */
    class ListItem(val title: String, val kClass: KClass<out Activity>) {
        override fun toString(): String {
            return title
        }
    }

    private val items = arrayOf(
            ListItem("Basic", BasicActivity::class),
            ListItem("Grid", GridActivity::class),
            ListItem("Multi-Holder", MultiTypeActivity::class),
            ListItem("Dynamic", DynamicActivity::class)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.setOnItemClickListener { _, _, position, _ -> showActivity(position) }
    }

    private fun showActivity(position: Int) {
        startActivity(Intent(this, items[position].kClass.java))
    }
}
