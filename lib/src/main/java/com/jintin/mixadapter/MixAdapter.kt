package com.jintin.mixadapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_ID
import android.view.ViewGroup
import java.util.*

/**
 * Compose your RecyclerView.Adapter into MutableList and dynamic dispatch the related function to each correspond RecyclerView.Adapter
 */
class MixAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T> {
    private val adapters: MutableList<RecyclerView.Adapter<out T>>

    constructor() {
        this.adapters = ArrayList()
    }

    constructor(adapters: MutableList<RecyclerView.Adapter<out T>>) {
        this.adapters = adapters
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

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
        adapters.forEach {
            it.setHasStableIds(hasStableIds)
        }
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver?) {
        super.registerAdapterDataObserver(observer)
        adapters.forEach {
            it.registerAdapterDataObserver(observer)
        }
    }

    override fun unregisterAdapterDataObserver(observer: RecyclerView.AdapterDataObserver?) {
        super.unregisterAdapterDataObserver(observer)
        adapters.forEach {
            it.unregisterAdapterDataObserver(observer)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var offset = 0
        var index = position
        adapters.forEach {
            if (index < it.itemCount) {
                return offset + it.getItemViewType(index)
            } else {
                offset += VIEW_TYPE_OFFSET
                index -= it.itemCount
            }
        }
        throw IllegalArgumentException("not found view type in adapters")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val index = viewType / VIEW_TYPE_OFFSET
        val position = viewType % VIEW_TYPE_OFFSET
        val adapter = adapters[index]
        return adapter.onCreateViewHolder(parent, position)
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        var offset = 0
        adapters.filterIsInstance<RecyclerView.Adapter<T>>()
                .forEach {
                    if (position - offset < it.itemCount) {
                        it.onBindViewHolder(holder, position - offset)
                        return
                    } else {
                        offset += it.itemCount
                    }
                }
    }

    override fun getItemCount(): Int = adapters.sumBy { it.itemCount }

    override fun getItemId(position: Int): Long {
        var offset = 0
        adapters.forEach {
            if (position - offset < it.itemCount) {
                return it.getItemId(position - offset)
            } else {
                offset += it.itemCount
            }
        }
        return NO_ID
    }

    /**
     * Add adapter into MixAdapter
     */
    fun addAdapter(adapter: RecyclerView.Adapter<out T>) {
        adapters.add(adapter)
        notifyDataSetChanged()
    }

    /**
     * Get start position of given adapter in MixAdapter
     */
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
