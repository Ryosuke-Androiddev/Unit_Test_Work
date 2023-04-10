package com.example.unit_test_work.domain.usecase.util

sealed class TodoItemOrder(val orderType: OrderType) {
    // data classは、親クラスが同じプロパティを持つことになるので、小クラスがdata classにできない
    class Title(orderType: OrderType): TodoItemOrder(orderType = orderType)
    class TodoId(orderType: OrderType): TodoItemOrder(orderType = orderType)
}
