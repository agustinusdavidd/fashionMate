package com.example.presentation.data.Data

import com.example.presentation.utils.Models.Pakaian

data class DataState(
    var data: MutableList<Pakaian> = mutableListOf(),
    var msg: String = "",
    var warna_kulit: String = "",
    var gender: String = ""
)
