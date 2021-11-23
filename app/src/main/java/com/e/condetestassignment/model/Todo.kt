package com.e.condetestassignment.model

import java.io.Serializable

data class Todo (
    val userId : Int = 0,
    val id: Int = 0,
    val title: String = "",
    val completed: Boolean = false
):Serializable