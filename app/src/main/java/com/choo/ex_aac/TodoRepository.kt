package com.choo.ex_aac

import android.content.Context
import androidx.lifecycle.LiveData

class TodoRepository(context: Context) {
    private val todoDao: TodoDao = TodoDatabase.getDatabase(context).todoDao()
    val todos: LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }
}