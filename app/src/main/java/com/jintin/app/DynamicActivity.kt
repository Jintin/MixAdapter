package com.jintin.app

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.jintin.app.adapter.StringAdapter
import com.jintin.mixadapter.MixAdapter

class DynamicActivity : BaseActivity() {

    private val itemsA = mutableListOf("A1", "A2", "A3", "A4", "A5")
    private val itemsB = mutableListOf("B1", "B2", "B3", "B4", "B5")
    private val itemsC = mutableListOf("C1", "C2", "C3", "C4", "C5")

    interface OnItemActionListener {
        fun onDelete()

        fun onClone()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Click to Add/Remove", Toast.LENGTH_LONG).show()
    }

    override fun setupRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun getAdapter(): RecyclerView.Adapter<StringAdapter.Holder> {
        val adapter = MixAdapter<StringAdapter.Holder>()
        val adapterA = StringAdapter(itemsA)
        setListener(itemsA, adapterA, adapter)
        val adapterB = StringAdapter(itemsB)
        setListener(itemsB, adapterB, adapter)
        val adapterC = StringAdapter(itemsC)
        setListener(itemsC, adapterC, adapter)
        adapter.addAdapter(adapterA)
        adapter.addAdapter(adapterB)
        adapter.addAdapter(adapterC)
        return adapter
    }

    private fun setListener(items: MutableList<String>, adapter: StringAdapter, mixAdapter: MixAdapter<StringAdapter.Holder>) {
        adapter.setItemClickListener(object : StringAdapter.OnAdapterItemClickListener {
            override fun onItemClick(position: Int) {
                val childPosition = position - mixAdapter.getAdapterOffset(adapter)
                showDialog(object : OnItemActionListener {
                    override fun onDelete() {
                        items.removeAt(childPosition)
                        mixAdapter.notifyItemRemoved(position)
                    }

                    override fun onClone() {
                        val string = items[childPosition]
                        items.add(childPosition + 1, string)
                        mixAdapter.notifyItemInserted(position + 1)
                    }
                })
            }
        })
    }

    private fun showDialog(listener: OnItemActionListener) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select action")
        builder.setItems(arrayOf("Delete", "Clone")) { dialog, which ->
            dialog.dismiss()
            when (which) {
                0 -> listener.onDelete()
                1 -> listener.onClone()
            }
        }
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

}
