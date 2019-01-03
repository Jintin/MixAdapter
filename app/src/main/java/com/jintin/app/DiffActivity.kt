package com.jintin.app

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.jintin.app.adapter.DiffAdapter
import com.jintin.app.adapter.StringAdapter
import com.jintin.mixadapter.MixAdapter

class DiffActivity : BaseActivity() {

    private val itemsA = arrayListOf("A1", "A2", "A3", "A4", "A5")
    private val itemsB = arrayListOf("B1", "B2", "B3", "B4", "B5")

    private val sortDirection = hashMapOf<RecyclerView.Adapter<*>, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Click to sort section ASC/DESC", Toast.LENGTH_LONG).show()
    }

    override fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun getAdapter(): RecyclerView.Adapter<out RecyclerView.ViewHolder> {
        val adapter = MixAdapter<StringAdapter.Holder>()
        addChild(itemsA, DiffAdapter(itemsA), adapter)
        addChild(itemsB, DiffAdapter(itemsB), adapter)
        return adapter
    }

    private fun addChild(items: MutableList<String>, adapter: DiffAdapter, mixAdapter: MixAdapter<StringAdapter.Holder>) {
        mixAdapter.addAdapter(adapter)
        sortDirection[adapter] = false
        adapter.setOnItemClickListener { _ ->
            val flag: Boolean = sortDirection[adapter] ?: false
            adapter.setItems(if (flag) items.sorted() else items.sortedDescending())
            sortDirection[adapter] = !flag
        }
    }
}