package com.myapp.firebasesample.presentation.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapp.firebasesample.R
import com.myapp.firebasesample.domain.model.entity.Employee

class EmployeeRecyclerViewAdapter (private val list: List<Employee>) : RecyclerView.Adapter<EmployeeRecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val positionText: TextView = itemView.findViewById(R.id.position)
        val titleText: TextView = itemView.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.positionText.text = position.toString()
        holder.titleText.text = list[position].first + list[position].last
    }

    override fun getItemCount(): Int = list.size
}