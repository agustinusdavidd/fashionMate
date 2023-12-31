package com.example.presentation.data.Data

import com.example.presentation.utils.Models.Pakaian

sealed class DataEvent {

    class Success(val data: MutableList<Pakaian>): DataEvent()
    class Failure(val msg: String): DataEvent()
    object Loading: DataEvent()
    object Empty: DataEvent()

    class filterForYou(val status: Boolean = false): DataEvent()
    class filterForMen(val status: Boolean = false): DataEvent()
    class filterForWomen(val status: Boolean = false): DataEvent()
    class filterAll(val status: Boolean = false): DataEvent()

    class onSearch(val query: String): DataEvent()
    class onFirstOpenSearch(): DataEvent()
}