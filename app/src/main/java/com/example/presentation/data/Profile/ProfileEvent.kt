package com.example.presentation.data.Profile

sealed class ProfileEvent {

    data class genderChanged(val gender: String) : ProfileEvent()
    data class skinChanged(val warna_kulit: String) : ProfileEvent()
    data class heightChange(val tinggi_badan: String): ProfileEvent()
    data class weightChange(val berat_badan: String): ProfileEvent()
    object savedButtonClicked: ProfileEvent()
    object profileOpened: ProfileEvent()

}