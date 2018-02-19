package com.jintin.app.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jintin.app.R
import com.jintin.app.adapter.ColorAdapter.ColorHolder

/**
 * multiple view holder type example
 */
class ColorAdapter(private val items: List<Color>) : RecyclerView.Adapter<ColorHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position % 3 + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorHolder? {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            TYPE_HOLDER1 -> {
                val view1 = inflater.inflate(R.layout.adapter_color, parent, false)
                return Holder1(view1)
            }
            TYPE_HOLDER2 -> {
                val view2 = inflater.inflate(R.layout.adapter_color2, parent, false)
                return Holder2(view2)
            }
            TYPE_HOLDER3 -> {
                val view3 = inflater.inflate(R.layout.adapter_color3, parent, false)
                return Holder3(view3)
            }
        }
        return null
    }

    override fun onBindViewHolder(holder: ColorHolder, position: Int) {
        val color = ContextCompat.getColor(holder.itemView.context, items[position].value)
        holder.bindColor(color)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    companion object {
        const val TYPE_HOLDER1 = 1
        const val TYPE_HOLDER2 = 2
        const val TYPE_HOLDER3 = 3
    }

    /**
     * Sample base holder
     */
    abstract class ColorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindColor(color: Int)
    }

    /**
     * Sample holder 1
     */
    private class Holder1 constructor(itemView: View) : ColorHolder(itemView) {
        private var colorView: View = itemView.findViewById(R.id.color)

        override fun bindColor(color: Int) {
            colorView.setBackgroundColor(color)
        }
    }

    /**
     * Sample holder 2
     */
    private class Holder2 constructor(itemView: View) : ColorHolder(itemView) {
        private var colorView: View = itemView.findViewById(R.id.color)
        private var colorView2: View = itemView.findViewById(R.id.color2)

        override fun bindColor(color: Int) {
            colorView.setBackgroundColor(color)
            colorView2.setBackgroundColor(color)
        }
    }

    /**
     * Sample holder 3
     */
    private class Holder3 constructor(itemView: View) : ColorHolder(itemView) {
        private var colorView: View = itemView.findViewById(R.id.color)
        private var colorView2: View = itemView.findViewById(R.id.color2)
        private var colorView3: View = itemView.findViewById(R.id.color3)

        override fun bindColor(color: Int) {
            colorView.setBackgroundColor(color)
            colorView2.setBackgroundColor(color)
            colorView3.setBackgroundColor(color)
        }
    }
}