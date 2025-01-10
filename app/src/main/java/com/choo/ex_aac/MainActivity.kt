package com.choo.ex_aac

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.choo.ex_aac.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // Database 및 ViewModel 설정
    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(TodoRepository(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView 어댑터 설정
        val adapter = TodoAdapter { todo -> todoViewModel.deleteTodo(todo) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        todoViewModel.todos.observe(this) { todos ->
            adapter.submitList(todos)
        }

        // Add 버튼 클릭 이벤트
        binding.addButton.setOnClickListener {
            val title = binding.editText.text.toString()
            if (title.isNotBlank()) {
                todoViewModel.addTodo(title)
                binding.editText.text.clear()
            }
        }
    }
}