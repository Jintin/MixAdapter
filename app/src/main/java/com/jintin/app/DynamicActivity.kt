package com.jintin.app

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.jintin.app.adapter.StringAdapter
import com.jintin.mixadapter.MixAdapter

/**
 * Dynamic add/remove RecyclerView data for MixAdapter
 */
class DynamicActivity : BaseActivity() {

    private val itemsA = mutableListOf("A1", "A2", "A3", "A4", "A5")
    private val itemsB = mutableListOf("B1", "B2", "B3", "B4", "B5")
    private val itemsC = mutableListOf("C1", "C2", "C3", "C4", "C5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Click to Add/Remove", Toast.LENGTH_LONG).show()
    }

    override fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun getAdapter(): MixAdapter<StringAdapter.Holder> {
        val adapter = MixAdapter<StringAdapter.Holder>()
        val adapterA = StringAdapter(itemsA)
        val adapterB = StringAdapter(itemsB)
        val adapterC = StringAdapter(itemsC)
        addChild(itemsA, adapterA, adapter)
        addChild(itemsB, adapterB, adapter)
        addChild(itemsC, adapterC, adapter)
        return adapter
    }

    private fun addChild(items: MutableList<String>, adapter: StringAdapter, mixAdapter: MixAdapter<StringAdapter.Holder>) {
        mixAdapter.addAdapter(adapter)
        adapter.setOnItemClickListener { position ->
            val childPosition = position - mixAdapter.getAdapterOffset(adapter)
            showDialog(onDelete = {
                items.removeAt(childPosition)
                mixAdapter.notifyItemRemoved(position)
            }, onClone = {
                val string = items[childPosition]
                items.add(childPosition + 1, string)
                mixAdapter.notifyItemInserted(position + 1)
            })
        }
    }

    private fun showDialog(onDelete: () -> Unit, onClone: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select action")
        builder.setItems(arrayOf("Delete", "Clone")) { dialog, which ->
            dialog.dismiss()
            when (which) {
                0 -> onDelete()
                1 -> onClone()
            }
        }
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

}
