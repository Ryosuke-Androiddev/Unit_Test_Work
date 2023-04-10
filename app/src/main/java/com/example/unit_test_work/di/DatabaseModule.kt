package com.example.unit_test_work.di

import android.app.Application
import androidx.room.Room
import com.example.unit_test_work.data.db.TodoDao
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
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: TodoDatabase) = database.todoDao()

    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDao): TodoRepository {
        // Domain層の抽象に依存した(Interfaceを実装した)クラスを返す
        return TodoRepositoryImpl(dao)
    }
}