package com.example.unit_test_work.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoItem(
    val title: String,
    @PrimaryKey val todoIte: Int
)
