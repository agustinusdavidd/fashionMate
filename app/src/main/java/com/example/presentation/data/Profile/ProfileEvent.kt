package com.example.presentation.data.Profile

import android.content.Context
import android.net.Uri
import com.example.presentation.utils.Models.UploadCloth

sealed class ProfileEvent {

    class Success(val data: MutableList<UploadCloth>): ProfileEvent()
    class Failure(val msg: String): ProfileEvent()
    object Loading: ProfileEvent()
    object Empty: ProfileEvent()
    data class genderChanged(val gender: String) : ProfileEvent()
    data class skinChanged(val warna_kulit: String) : ProfileEvent()
    data class heightChange(val tinggi_badan: String): ProfileEvent()
    data class weightChange(val berat_badan: String): ProfileEvent()
    object savedButtonClicked: ProfileEvent()
    object profileOpened: ProfileEvent()
    data class uploadClicked(val uri: Uri, val context: Context): ProfileEvent()
    data class filterSaved(val status: Boolean = false): ProfileEvent()
    data class filterUpload(val status: Boolean = false): ProfileEvent()
    data class clothSaved(val photo_url: String): ProfileEvent()
    data class checkIsSaved(val photo_url: String): ProfileEvent()

}