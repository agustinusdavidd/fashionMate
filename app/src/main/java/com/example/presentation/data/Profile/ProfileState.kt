package com.example.presentation.data.Profile

data class ProfileState(
    var email: String,
    var nama: String,

    var gender: String = "pria",
    var warna_kulit: String = "normal",
    var tinggi_badan: Int = 0,
    var berat_badan: Int = 0,

    var tinggi_badan_error: Boolean = false,
    var berat_badan_error: Boolean = false,

    var firstTime: Boolean
)