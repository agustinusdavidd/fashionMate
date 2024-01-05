package com.example.presentation.utils.Models

data class CalendarInput(
    val day: Int,
    val savedClothPlanned: List<SavedCloth> = emptyList()
)
