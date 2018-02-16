package com.jintin.app

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jintin.app.adapter.Color
import com.jintin.app.adapter.ColorAdapter
import com.jintin.app.adapter.StringAdapter
import com.jintin.mixadapter.MixAdapter

/**
 * Multi holder example of MixAdapter
 */
class MultiTypeActivity : BaseActivity() {

    private val itemsA = listOf("A1", "A2", "A3", "A4", "A5")
    private val itemsB = listOf("B1", "B2", "B3", "B4", "B5")
    private val itemsC = listOf("C1", "C2", "C3", "C4", "C5")

    private val items1 = listOf(Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.PURPLE)
    private val items2 = listOf(Color.GREEN, Color.PURPLE, Color.RED, Color.BLUE, Color.ORANGE)
    private val items3 = listOf(Color.PURPLE, Color.ORANGE, Color.BLUE, Color.GREEN, Color.RED)

    override fun setupRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun getAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        val adapterA = StringAdapter(itemsA)
        val adapterB = StringAdapter(itemsB)
        val adapterC = StringAdapter(itemsC)
        val adapter1 = ColorAdapter(this, items1)
        val adapter2 = ColorAdapter(this, items2)
        val adapter3 = ColorAdapter(this, items3)
        return MixAdapter(mutableListOf(adapterA, adapter1, adapterB, adapter2, adapterC, adapter3))
    }

}
