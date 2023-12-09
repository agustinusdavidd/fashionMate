package com.example.presentation.data.Main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val searchQuery = mutableStateOf("")

    fun onSearchTextChange(text: String) {
        searchQuery.value = text
    }

}