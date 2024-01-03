package com.example.presentation.utils.Models

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toUpperCase

data class Pakaian(

    var gender: String? = null,
    var isSaved: Boolean? = null,
    var jenis: String? = null,
    var kategori: String? = null,
    val keyword: String? = null,
    var photo_url: String? = null,
    var tipe: String? = null,
    var warna: String? = null

) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$keyword",
            "$keyword".trim(),
            "$keyword".uppercase(),
            "$keyword".lowercase()
        )

        return matchingCombinations.any() {
            it.contains(query, ignoreCase = true)
        }
    }
}
