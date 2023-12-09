package com.example.presentation.utils

data class Pakaian(

    val id: String,
    val user_id: String,
    val color: String,
    val category: String,
    val type: String,
    val gender: String,
    val keyword: String

) {
    fun doesMatchSearchQuery(query: String): Boolean {
        return true
    }
}
