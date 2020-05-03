package com.jintin.mixadapter

import android.support.v7.widget.RecyclerView

class NestedAdapterDataObserver(private val parentAdapter: MixAdapter<*>?,
                                private val childAdapter: RecyclerView.Adapter<*>?) : RecyclerView.AdapterDataObserver() {

    override fun onChanged() {
        super.onChanged()
        if (parentAdapter == null) return
        parentAdapter.notifyDataSetChanged()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        super.onItemRangeChanged(positionStart, itemCount)

        if (parentAdapter == null || childAdapter == null) return

        val adapterPosition = parentAdapter.getAdapterOffset(childAdapter)
        parentAdapter.notifyItemRangeChanged(adapterPosition + positionStart, itemCount)

    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        super.onItemRangeChanged(positionStart, itemCount, payload)

        if (parentAdapter == null || childAdapter == null) return

        val adapterPosition = parentAdapter.getAdapterOffset(childAdapter)
        parentAdapter.notifyItemRangeChanged(adapterPosition + positionStart, itemCount, payload)
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)

        if (parentAdapter == null || childAdapter == null) return

        val adapterPosition = parentAdapter.getAdapterOffset(childAdapter)
        parentAdapter.notifyItemRangeInserted(adapterPosition + positionStart, itemCount)
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        super.onItemRangeRemoved(positionStart, itemCount)

        if (parentAdapter == null || childAdapter == null) return

        val adapterPosition = parentAdapter.getAdapterOffset(childAdapter)
        parentAdapter.notifyItemRangeRemoved(adapterPosition + positionStart, itemCount)
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        super.onItemRangeMoved(fromPosition, toPosition, itemCount)

        if (parentAdapter == null || childAdapter == null) return

        val adapterPosition = parentAdapter.getAdapterOffset(childAdapter)
        parentAdapter.notifyItemMoved(adapterPosition + fromPosition, adapterPosition + toPosition)
    }
}