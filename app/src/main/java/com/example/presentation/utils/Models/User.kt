package com.example.presentation.utils.Models

data class User(
    val nama: String = "",
    val email: String = "",
    var berat_badan: String? = null,
    var jenis_kelamin: String? = null,
    var tinggi_badan: String? = null,
    var warna_kulit: String? = null
)