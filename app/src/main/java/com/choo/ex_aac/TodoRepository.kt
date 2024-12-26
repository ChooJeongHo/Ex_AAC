package com.choo.ex_aac

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {
    val todos: LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }
}