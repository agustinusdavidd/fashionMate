package com.example.presentation.data.Profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.presentation.data.rules.Validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.security.MessageDigest

class ProfileViewModel() : ViewModel() {

    var profileState = mutableStateOf(ProfileState())
    var validationStatus = mutableStateOf(false)
    var progress = mutableStateOf(false)
    val db = FirebaseDatabase.getInstance().getReference("users")

    fun onEvent(event: ProfileEvent){

        when(event){
            is ProfileEvent.genderChanged -> {
                profileState.value = profileState.value.copy(
                    gender = event.gender
                )
            }
            is ProfileEvent.skinChanged -> {
                profileState.value = profileState.value.copy(
                    warna_kulit = event.warna_kulit
                )
            }
            is ProfileEvent.heightChange -> {
                profileState.value = profileState.value.copy(
                    tinggi_badan = event.tinggi_badan.toInt()
                )
            }
            is ProfileEvent.weightChange -> {
                profileState.value = profileState.value.copy(
                    tinggi_badan = event.berat_badan.toInt()
                )
            }
            is ProfileEvent.savedButtonClicked -> {
                updateData()
            }
            is ProfileEvent.profileOpened -> {
                getUserName()
            }
        }
        validateData()
    }

    private fun updateData() {

        progress.value = true

        val email = profileState.value.email
        val nama = profileState.value.nama
        val gender = profileState.value.gender
        val warna_kulit = profileState.value.warna_kulit
        val tinggi_badan = profileState.value.tinggi_badan.toString()
        val berat_badan = profileState.value.berat_badan.toString()

        val user = mapOf<String, String>(
            "jenis_kelamin" to gender,
            "warna_kulit" to warna_kulit,
            "tinggi_badan" to tinggi_badan,
            "berat_badan" to berat_badan
        )

        db.child(sha256(email)).updateChildren(user).addOnSuccessListener {
            progress.value = false
            Log.d("Update Data", "Done")
        }.addOnFailureListener {
            Log.d("Update Data", "Failed")
        }
    }

    fun getUserName(){

        progress.value = true

        val currentUser = FirebaseAuth.getInstance().currentUser
        val email = currentUser?.email

        if(email != null) {
            profileState.value = profileState.value.copy(
                email = email
            )
        }

        val shaEmail = sha256(profileState.value.email)

        db.child(shaEmail).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(userSnapshot in snapshot.children){
                    if(userSnapshot.key == "nama"){
                        val namaVal = userSnapshot.getValue(String::class.java)

                        profileState.value = profileState.value.copy(
                            nama = namaVal!!
                        )

                        Log.d("Get User Data Nama", profileState.value.nama)

                        Log.d("Get User Data data email", profileState.value.nama + " " + profileState.value.email)
                    }

//                    if(userSnapshot.key == "email"){
//                        val emailVal = userSnapshot.getValue(String::class.java)
//                        if(emailVal != null){
//                            profileState.value = profileState.value.copy(
//                                email = emailVal
//                            )
//
//                            Log.d("Get User Data email", emailVal)
//                        }
//                    }
                }
                progress.value = false
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error Profile View Model", error.message)
            }
        })
    }

    private fun validateData() {

        val isHeightValid = Validator.validateHeight(
            height = profileState.value.tinggi_badan
        )

        val isWeightValid = Validator.validateWeight(
            weight = profileState.value.berat_badan
        )

        profileState.value = profileState.value.copy(
            tinggi_badan_error = isHeightValid.status,
            berat_badan_error = isWeightValid.status
        )

        validationStatus.value = isHeightValid.status && isWeightValid.status
    }

    fun sha256(input: String?): String {

        val bytes = input?.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it ->
            str + "%02x".format(it)
        })
    }
}