package com.jintin.mixadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import java.lang.RuntimeException
import java.util.*

class MixAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T> {
    private val adapters: MutableList<RecyclerView.Adapter<out T>>

    constructor() {
        this.adapters = ArrayList()
    }

    constructor(adapters: MutableList<RecyclerView.Adapter<out T>>) {
        this.adapters = adapters
    }

    fun addAdapter(adapter: RecyclerView.Adapter<out T>) {
        adapters.add(adapter)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        adapters.forEach {
            it.onAttachedToRecyclerView(recyclerView)
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        adapters.forEach {
            it.onDetachedFromRecyclerView(recyclerView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var offset = 0
        for (i in adapters.indices) {
            val adapter = adapters[i]
            if (position < offset + adapter.itemCount) {
                return VIEW_TYPE_OFFSET * i + adapter.getItemViewType(position - offset)
            } else {
                offset += adapter.itemCount
            }
        }
        throw RuntimeException("getItemViewType size exceed")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val index = viewType / VIEW_TYPE_OFFSET
        val position = viewType % VIEW_TYPE_OFFSET
        val adapter = adapters[index]
        return adapter.onCreateViewHolder(parent, position)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        var offset = 0
        adapters.indices
                .asSequence()
                .map { adapters[it] as RecyclerView.Adapter<T> }
                .forEach {
                    if (position < offset + it.itemCount) {
                        it.onBindViewHolder(holder, position - offset)
                        return
                    } else {
                        offset += it.itemCount
                    }
                }
    }

    override fun getItemCount(): Int {
        return adapters.sumBy { it.itemCount }
    }

    fun getAdapterOffset(target: RecyclerView.Adapter<*>): Int {
        var offset = 0
        for (adapter in adapters) {
            if (adapter == target) {
                return offset
            }
            offset += adapter.itemCount
        }
        return offset
    }

    companion object {
        private const val VIEW_TYPE_OFFSET = 10000
    }
}
