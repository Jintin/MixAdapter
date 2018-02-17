package com.jintin.app

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.jintin.app.adapter.StringAdapter
import com.jintin.mixadapter.MixAdapter

/**
 * Grid layout example of MixAdapter
 */
class GridActivity : BaseActivity() {

    private val itemsA = listOf("A1", "A2", "A3", "A4", "A5")
    private val itemsB = listOf("B1", "B2", "B3", "B4", "B5")
    private val itemsC = listOf("C1", "C2", "C3", "C4", "C5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Click to get Index", Toast.LENGTH_SHORT).show()
    }

    override fun setupRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
    }

    override fun getAdapter(): MixAdapter<StringAdapter.Holder> {
        val adapter = MixAdapter<StringAdapter.Holder>()
        val adapterA = StringAdapter(itemsA)
        val adapterB = StringAdapter(itemsB)
        val adapterC = StringAdapter(itemsC)
        addChild(adapter, adapterA)
        addChild(adapter, adapterB)
        addChild(adapter, adapterC)

        return adapter
    }

    private fun addChild(mixAdapter: MixAdapter<StringAdapter.Holder>, adapter: StringAdapter) {
        mixAdapter.addAdapter(adapter)
        adapter.setOnItemClickListener { position ->
            val childPosition = position - mixAdapter.getAdapterOffset(adapter)
            showToast(childPosition, position)
        }
    }

    private fun showToast(childPosition: Int, position: Int) {
        Toast.makeText(this, "child: $childPosition, global: $position", Toast.LENGTH_SHORT).show()
    }
}
