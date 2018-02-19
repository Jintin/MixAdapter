package com.jintin.app.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jintin.app.R

/**
 * basic adapter example
 */
class StringAdapter(private val items: List<String>) : RecyclerView.Adapter<StringAdapter.Holder>() {
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_string, parent, false)
        val holder = Holder(view)
        view.setOnClickListener {
            val position = holder.adapterPosition
            onItemClickListener?.invoke(position)
        }
        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * callback of adapter item click listener
     */
    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        this.onItemClickListener = listener
    }

    /**
     * Sample holder
     */
    class Holder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView: TextView = itemView.findViewById(R.id.text)
    }
}
