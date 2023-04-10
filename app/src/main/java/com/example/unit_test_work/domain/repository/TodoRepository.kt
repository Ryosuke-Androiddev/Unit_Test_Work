package com.example.unit_test_work.domain.repository

import com.example.unit_test_work.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodo(): Flow<List<TodoItem>>
    // テストのルールでリストに追加する時に使うので記述する(テストは書かない)
    suspend fun insertTodoItem(todoItem: TodoItem)
}