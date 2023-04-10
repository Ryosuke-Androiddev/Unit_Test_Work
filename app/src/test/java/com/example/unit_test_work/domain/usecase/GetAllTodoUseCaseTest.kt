package com.example.unit_test_work.domain.usecase

import com.example.unit_test_work.data.repository.FakeTodoRepository
import com.example.unit_test_work.domain.model.TodoItem
import com.example.unit_test_work.domain.usecase.util.OrderType
import com.example.unit_test_work.domain.usecase.util.TodoItemOrder
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllTodoUseCaseTest {

    private lateinit var getAllTodoUseCase: GetAllTodoUseCase
    private lateinit var fakeTodoRepository: FakeTodoRepository

    // 初期設定(初期化遅延のオブジェクト生成、FakeRepoから返却されるデータの作成)
    @Before
    fun setup() {
        fakeTodoRepository = FakeTodoRepository()

        // 抽象に依存させることでテストが書きやすい
        getAllTodoUseCase = GetAllTodoUseCase(repository = fakeTodoRepository)

        val createDummyTodoList = mutableListOf<TodoItem>()
        ('a'..'z').forEachIndexed { index, c ->
            createDummyTodoList.add(
                TodoItem(
                    title = c.toString(),
                    todoId = index
                )
            )
        }

        // 正しくソートされるかを確認するために、シャッフルする
        createDummyTodoList.shuffle()
        runBlocking {
            createDummyTodoList.forEach { todoItem ->
                fakeTodoRepository.insertTodoItem(todoItem = todoItem)
            }
        }
    }

    @Test
    fun `Order todoList by title ascending, absolutely correct order`() = runBlocking {
        val todoList = getAllTodoUseCase(TodoItemOrder.Title(OrderType.Ascending)).first()

        for(i in 0..todoList.size - 2) {
            assertThat(todoList[i].title).isLessThan(todoList[i+1].title)
        }
    }

    @Test
    fun `Order todoList by title descending, absolutely correct order`() = runBlocking {
        val todoList = getAllTodoUseCase(TodoItemOrder.Title(OrderType.Descending)).first()

        for(i in 0..todoList.size - 2) {
            assertThat(todoList[i].title).isGreaterThan(todoList[i+1].title)
        }
    }

    @Test
    fun `Order todoList by todoId ascending, absolutely correct order`() = runBlocking {
        val todoList = getAllTodoUseCase(TodoItemOrder.Title(OrderType.Ascending)).first()

        for(i in 0..todoList.size - 2) {
            assertThat(todoList[i].todoId).isLessThan(todoList[i+1].todoId)
        }
    }

    @Test
    fun `Order todoList by todoId descending, absolutely correct order`() = runBlocking {
        val todoList = getAllTodoUseCase(TodoItemOrder.Title(OrderType.Descending)).first()

        for(i in 0..todoList.size - 2) {
            assertThat(todoList[i].todoId).isGreaterThan(todoList[i+1].todoId)
        }
    }
}