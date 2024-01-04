package com.example.presentation.data.Profile

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.lifecycle.ViewModel
import com.example.presentation.data.rules.Validator
import com.example.presentation.utils.Models.SavedCloth
import com.example.presentation.utils.Models.UploadCloth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.security.MessageDigest
import java.util.UUID

class ProfileViewModel : ViewModel() {

    var profileState = mutableStateOf(ProfileState(email = "", nama = "", firstTime = true))
    private var validationStatus = mutableStateOf(false)
    var progress = mutableStateOf(false)
    private val dbUser = FirebaseDatabase.getInstance().getReference("users")
    private val shaEmail = sha256(profileState.value.email)
    private val dbCloth = FirebaseDatabase.getInstance().getReference("uploadCloth")
    private val dbUploadCloth = dbCloth.child(shaEmail)
    private val dbSaved = FirebaseDatabase.getInstance().getReference("savedCloth").child(shaEmail)

    val response: MutableState<ProfileEvent> = mutableStateOf(ProfileEvent.Empty)

    var isSavedByUser = mutableStateOf(false)


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
                    berat_badan = event.berat_badan.toInt()
                )
            }
            is ProfileEvent.savedButtonClicked -> {
                updateData()
            }
            is ProfileEvent.profileOpened -> {
                getUserName()
            }
            is ProfileEvent.uploadClicked -> {
                uploadImage(event.uri, event.context)
            }
            is ProfileEvent.filterSaved -> {
                fetchSavedData()
            }
            is ProfileEvent.filterUpload -> {
                fetchUploadData()
            }
            is ProfileEvent.clothSaved -> {
                savedCloth(event.photo_url)
            }
            is ProfileEvent.checkIsSaved -> {

            }
            else -> {
                Log.d("Profile View Model", "Another event : ${event.toString()}")
            }
        }
        validateData()
    }

    private fun getUUID(photo_url: String) {
        var listUUID = mutableListOf<String>()

        dbSaved.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(uuidSnapshot in snapshot.children) {
                    val uuid = uuidSnapshot.key
                    if(uuid != null) {
                        if(!listUUID.contains(uuid)){
                            listUUID.add(uuid)
                        }
                        Log.d("Check is saved", "uuid : $uuid")
                    }
                }
                val isSaved = checkIsSaved(listUUID, photo_url)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Check is saved", "Error : ${error.message}")
            }
        })
    }

    private fun checkIsSaved(listUUID: MutableList<String>, photo_url: String) {
        for (u in listUUID){
            dbSaved.child(u).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val isSaved = snapshot.child("photo_url").getValue(String::class.java) == photo_url
                    if(isSaved){
                        isSavedByUser.value = true
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("Check is saved", "Cancelled")
                }
            })
        }
    }

    private fun savedCloth(photo_url: String) {

        val TAG = "Saved Cloth"
        val savedCloth = SavedCloth(photo_url = photo_url)
        var fileName = ""

        val startIndex = photo_url.lastIndexOf("/o/") + 3
        val endIndex = photo_url.indexOf(".jpg")

        if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
            fileName = photo_url.substring(startIndex, endIndex)
        }

        dbSaved.child(fileName).setValue(savedCloth)
            .addOnCompleteListener {

                if(it.isSuccessful){
                    Log.d(TAG, "Object created")
                }
            }
            .addOnCanceledListener {
                Log.d(TAG, "Canceled")
            }
            .addOnFailureListener{
                Log.d(TAG, "Failure")
                Log.d(TAG, "${it.message}")
            }
    }

    private fun fetchSavedData() {
        val tempList = mutableListOf<UploadCloth>()
        response.value = ProfileEvent.Loading

        dbSaved.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnap in snapshot.children) {
                    val valuePakaian = dataSnap.getValue(UploadCloth::class.java)
                    Log.d("Fetch Saved Data", "Value : $valuePakaian")
                    if(valuePakaian != null) {
                        tempList.add(valuePakaian)
                    }
                }
                response.value = ProfileEvent.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = ProfileEvent.Failure(error.message)
            }
        })
    }

    private fun fetchUploadData() {
        val tempList = mutableListOf<UploadCloth>()
        response.value = ProfileEvent.Loading

        dbCloth.child(shaEmail).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnap in snapshot.children) {
                    val valuePakaian = dataSnap.getValue(UploadCloth::class.java)
                    if(valuePakaian != null) {
                        tempList.add(valuePakaian)
                    }
                }
                response.value = ProfileEvent.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = ProfileEvent.Failure(error.message)
            }
        })
    }

    private fun uploadImage(uri: Uri, context: Context) {

        val TAG = "Upload Image"
        val storage = Firebase.storage
        val storagRef = storage.reference

        val uniqueName = UUID.randomUUID()
        val spaceRef = storagRef.child("images/$uniqueName")

        val byteArray: ByteArray? = context.contentResolver
            .openInputStream(uri)
            ?.use { it.readBytes() }

        var imageUrl: String

        byteArray?.let {
            val uploadTask = spaceRef.putBytes(byteArray)
            uploadTask
                .addOnFailureListener {}
                .addOnSuccessListener {taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                    imageUrl = it.toString()

                    val uploadCloth = UploadCloth(photo_url = imageUrl)

                    dbUploadCloth.child(uniqueName.toString()).setValue(uploadCloth)
                        .addOnCompleteListener {

                            if(it.isSuccessful){
                                Log.d(TAG, "Object created")
                            }
                        }
                        .addOnCanceledListener {
                            Log.d(TAG, "Canceled")
                        }
                        .addOnFailureListener{
                            Log.d(TAG, "Failure")
                            Log.d(TAG, "${it.message}")
                        }
                }
            }
        }
    }

    private fun updateData() {

        progress.value = true

        val email = profileState.value.email
        val gender = profileState.value.gender
        val warnaKulit = profileState.value.warna_kulit
        val tinggiBadan = profileState.value.tinggi_badan.toString()
        val beratBadan = profileState.value.berat_badan.toString()

        val user = mapOf(
            "jenis_kelamin" to gender,
            "warna_kulit" to warnaKulit,
            "tinggi_badan" to tinggiBadan,
            "berat_badan" to beratBadan
        )

        dbUser.child(sha256(email)).updateChildren(user).addOnSuccessListener {
            progress.value = false
            Log.d("Update Data", "Done")
        }.addOnFailureListener {
            Log.d("Update Data", "Failed")
        }
    }

    private fun getUserName() : String{

        progress.value = true

        val currentUser = FirebaseAuth.getInstance().currentUser
        val email: String? = currentUser?.email

        if(email != null) {
            profileState.value = profileState.value.copy(
                email = email
            )
        }

        val shaEmail = sha256(profileState.value.email)

        dbUser.child(shaEmail).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(userSnapshot in snapshot.children){
                    when(userSnapshot.key) {
                        "nama" -> {
                            val namaVal = userSnapshot.getValue(String::class.java)

                            profileState.value = profileState.value.copy(
                                nama = namaVal!!
                            )
                        }
                        "jenis_kelamin" -> {
                            val genderVal = userSnapshot.getValue(String::class.java)

                            profileState.value = profileState.value.copy(
                                gender = genderVal!!
                            )
                        }
                        "tinggi_badan" -> {
                            val heightVal = userSnapshot.getValue(String::class.java)

                            profileState.value = profileState.value.copy(
                                tinggi_badan = heightVal!!.toInt()
                            )
                        }
                        "warna_kulit" -> {
                            val skinVal = userSnapshot.getValue(String::class.java)

                            profileState.value = profileState.value.copy(
                                warna_kulit = skinVal!!
                            )
                        }
                        "berat_badan" -> {
                            val weightVal = userSnapshot.getValue(String::class.java)

                            profileState.value = profileState.value.copy(
                                berat_badan = weightVal!!.toInt()
                            )
                        }
                    }
                }
                progress.value = false
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error Profile View Model", error.message)
            }
        })

        return profileState.value.nama
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

    private fun sha256(input: String?): String {

        val bytes = input?.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it ->
            str + "%02x".format(it)
        })
    }
}