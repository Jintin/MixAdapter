package com.jintin.app

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jintin.app.adapter.StringAdapter
import com.jintin.mixadapter.MixAdapter
import java.util.*

class BasicActivity : BaseActivity() {

    private val itemsA = Arrays.asList("A1", "A2", "A3", "A4", "A5")
    private val itemsB = Arrays.asList("B1", "B2", "B3", "B4", "B5")
    private val itemsC = Arrays.asList("C1", "C2", "C3", "C4", "C5")

    override fun setupRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun getAdapter(): RecyclerView.Adapter<StringAdapter.Holder> {
        val adapter = MixAdapter<StringAdapter.Holder>()
        val adapterA = StringAdapter(itemsA)
        val adapterB = StringAdapter(itemsB)
        val adapterC = StringAdapter(itemsC)
        adapter.addAdapter(adapterA)
        adapter.addAdapter(adapterB)
        adapter.addAdapter(adapterC)
        return adapter
    }
}
