package com.example.unit_test_work.di

import com.example.unit_test_work.data.db.TodoDatabase
import com.example.unit_test_work.data.repository.TodoRepositoryImpl
import com.example.unit_test_work.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        // Domain層の抽象に依存した(Interfaceを実装した)クラスを返す
        return TodoRepositoryImpl(db.todoDao)
    }
}