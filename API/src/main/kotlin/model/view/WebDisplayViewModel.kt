package com.pos.model.view

data class WebDisplayViewModel<T>(
    val result : Boolean,
    val message : String,
    val response : T?
)