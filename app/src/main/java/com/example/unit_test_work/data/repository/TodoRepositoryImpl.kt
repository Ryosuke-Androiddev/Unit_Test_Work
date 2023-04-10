package com.example.unit_test_work.data.repository

import com.example.unit_test_work.data.db.TodoDao
import com.example.unit_test_work.domain.model.TodoItem
import com.example.unit_test_work.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val todoDao: TodoDao
): TodoRepository {

    override fun getAllTodo(): Flow<List<TodoItem>> {
        return todoDao.getAllTodo()
    }

    override suspend fun insertTodoItem(todoItem: TodoItem) {
        todoDao.insertTodoItem(todoItem = todoItem)
    }
}