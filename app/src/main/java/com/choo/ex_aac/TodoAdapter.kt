package com.choo.ex_aac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val onDeleteClick: (Todo) -> Unit) :
    ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position), onDeleteClick)
    }

    class TodoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        private val deleteButton: Button = view.findViewById(R.id.deleteButton)

        fun bind(todo: Todo, onDeleteClick: (Todo) -> Unit) {
            titleTextView.text = todo.title
            deleteButton.setOnClickListener {
                onDeleteClick(todo)
            }
        }
    }

    class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean = oldItem == newItem
    }
}