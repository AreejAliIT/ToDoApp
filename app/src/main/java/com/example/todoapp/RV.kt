package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


    class Tasks(private val context: Context, private val tasks:ArrayList<String>) :
        RecyclerView.Adapter<Tasks.ViewHolder>(){

        class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)
        // for binding
        private lateinit var tvTask : TextView
        // to inflate the item_row.xml
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_row,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var task = tasks[position]
            holder.itemView.apply {
                // for binding
                tvTask = findViewById(R.id.tvRV)
                tvTask.text = task
            }
        }
        override fun getItemCount() = tasks.size
    }
