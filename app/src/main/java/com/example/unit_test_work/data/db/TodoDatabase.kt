package com.example.unit_test_work.data.db

import androidx.room.Database
import com.example.unit_test_work.domain.model.TodoItem

@Database(
    entities = [TodoItem::class],
    version = 1
)
abstract class TodoDatabase {

    abstract val todoDao: TodoDao

    companion object {
        const val DATABASE_NAME = "todo_db"
    }
}