package com.exmple.slidingdemo.fourway

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.exmple.slidingdemo.R

class RecyclerviewAdapter(private val context: Context, private val data: List<String>) : RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView
        init {
            name = itemView.findViewById(R.id.item_fragment_flea_tv_userName)

        }
    }
}
