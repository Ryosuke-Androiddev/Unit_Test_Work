package com.example.unit_test_work.domain.usecase

import com.example.unit_test_work.domain.model.TodoItem
import com.example.unit_test_work.domain.repository.TodoRepository
import com.example.unit_test_work.domain.usecase.util.OrderType
import com.example.unit_test_work.domain.usecase.util.TodoItemOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// このクラスをテスト対象にする
class GetAllTodoUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    operator fun invoke(
        todoItemOrder: TodoItemOrder = TodoItemOrder.TodoId(orderType = OrderType.Descending)
    ): Flow<List<TodoItem>> {
        // mapを使ってソートしたリストに変換
        return repository.getAllTodo().map { todoList ->
            when (todoItemOrder.orderType) {
                is OrderType.Ascending -> {
                    when (todoItemOrder) {
                        is TodoItemOrder.Title -> todoList.sortedBy { it.title.lowercase() }
                        is TodoItemOrder.TodoId -> todoList.sortedBy { it.todoId }
                    }
                }
                is OrderType.Descending -> {
                    when (todoItemOrder) {
                        is TodoItemOrder.Title -> todoList.sortedByDescending { it.title.lowercase() }
                        is TodoItemOrder.TodoId -> todoList.sortedByDescending { it.todoId }
                    }
                }
            }
        }
    }
}