package com.choo.ex_aac

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room


class MainActivity : AppCompatActivity() {
    // Database 및 ViewModel 설정
    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(TodoRepository(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView 어댑터 설정
        val adapter = TodoAdapter { todo -> todoViewModel.deleteTodo(todo) }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) // RecyclerView 연결
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        todoViewModel.todos.observe(this) { todos ->
            adapter.submitList(todos)
        }

        // Add 버튼 클릭 이벤트
        val addButton = findViewById<Button>(R.id.addButton) // Add 버튼 연결
        val editText = findViewById<EditText>(R.id.editText) // EditText 연결
        addButton.setOnClickListener {
            val title = editText.text.toString()
            if (title.isNotBlank()) {
                todoViewModel.addTodo(title)
                editText.text.clear()
            }
        }
    }
}