package com.jintin.app

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jintin.app.adapter.StringAdapter
import com.jintin.mixadapter.MixAdapter

/**
 * Simple usage of MixAdapter
 */
class BasicActivity : BaseActivity() {

    private val itemsA = listOf("A1", "A2", "A3", "A4", "A5")
    private val itemsB = listOf("B1", "B2", "B3", "B4", "B5")
    private val itemsC = listOf("C1", "C2", "C3", "C4", "C5")

    override fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun getAdapter(): MixAdapter<StringAdapter.Holder> {
        val adapter = MixAdapter<StringAdapter.Holder>()
        adapter.addAdapter(StringAdapter(itemsA))
        adapter.addAdapter(StringAdapter(itemsB))
        adapter.addAdapter(StringAdapter(itemsC))
        return adapter
    }
}
