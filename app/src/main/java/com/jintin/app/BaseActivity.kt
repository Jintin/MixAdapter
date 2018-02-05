package com.jintin.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        setupRecyclerView(recyclerView)
        recyclerView.adapter = getAdapter()
    }

    protected abstract fun setupRecyclerView(recyclerView: RecyclerView)

    protected abstract fun getAdapter(): RecyclerView.Adapter<*>
}
