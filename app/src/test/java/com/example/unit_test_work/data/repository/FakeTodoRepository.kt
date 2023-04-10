package com.example.unit_test_work.data.repository

import com.example.unit_test_work.domain.model.TodoItem
import com.example.unit_test_work.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTodoRepository: TodoRepository {

    // UseCase側のsetupしたデータと比較
    // ここにデータを格納して、あたかもこのクラスがデータベースとやりとりしているように振る舞わせる
    private val todoList = mutableListOf<TodoItem>()

    override fun getAllTodo(): Flow<List<TodoItem>> {
        return flow { emit(todoList) }
    }

    // ルールを書く時に必須になる処理(ここの部分に関してはテストを行わない)
    override suspend fun insertTodoItem(todoItem: TodoItem) {
        todoList.add(todoItem)
    }
}
