package com.example.unit_test_work.domain.usecase.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
