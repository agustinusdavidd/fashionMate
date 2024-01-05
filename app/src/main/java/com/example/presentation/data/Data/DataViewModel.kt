package com.example.presentation.data.Data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.presentation.utils.Models.Pakaian
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.security.MessageDigest

class DataViewModel : ViewModel(){

    val response: MutableState<DataEvent> = mutableStateOf(DataEvent.Empty)
    var progress = mutableStateOf(false)
    private val db = FirebaseDatabase.getInstance().getReference("pakaian")
    private val dbUser = FirebaseDatabase.getInstance().getReference("users")
    var dataState = mutableStateOf(DataState())

    fun onEvent(event : DataEvent){
        when(event) {
            is DataEvent.filterForYou -> {
                response.value = DataEvent.Loading
                getUserData()
                fetchForYouDataFromFirebase()
            }
            is DataEvent.filterForMen -> {
                fetchMenDataFromFirebase()
            }
            is DataEvent.filterForWomen -> {
                fetchWomenDataFromFirebase()
            }
            is DataEvent.onSearch -> {
                fetchDataFromFirebaseByQuery(event.query)
            }
            is DataEvent.onFirstOpenSearch -> {
                fetchAllDataFromFirebase()
            }
            else -> {
                fetchAllDataFromFirebase()
            }
        }
    }

    private fun fetchForYouDataFromFirebase() {
        val tempList = mutableListOf<Pakaian>()
        val listWarnaBright = listOf("putih", "kuning", "pink", "merah", "ungu", "hijau", "biru", "hitam")
        val listWarnaNormal = listOf("maroon", "cokelat", "pink", "ungu", "hijau", "biru", "hitam", "abu-abu")
        val listWarnaDark = listOf("putih", "kuning", "merah", "ungu", "pink", "biru", "orange")

        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnap in snapshot.children) {
                    val pakaianItem = dataSnap.getValue(Pakaian::class.java)
                    if(pakaianItem != null && pakaianItem.gender == dataState.value.gender) {
                        Log.d("Warna kulit", "warna : ${dataState.value.warna_kulit}")
                        when(dataState.value.warna_kulit) {
                            "slightly bright" -> {
                                if(listWarnaBright.contains(pakaianItem.warna)){
                                    if(!tempList.contains(pakaianItem)){
                                        tempList.add(pakaianItem)
                                    }
                                }
                            }
                            "bright" -> {
                                if(!tempList.contains(pakaianItem)){
                                    tempList.add(pakaianItem)
                                }
                            }
                            "normal" -> {
                                if(!tempList.contains(pakaianItem)){
                                    tempList.add(pakaianItem)
                                }
                            }
                            "slightly dark" -> {
                                if(!tempList.contains(pakaianItem)){
                                    tempList.add(pakaianItem)
                                }
                            }
                            "dark" -> {
                                if(!tempList.contains(pakaianItem)){
                                    tempList.add(pakaianItem)
                                }
                            }
                        }
                        tempList.add(pakaianItem)
                    }
                }
                response.value = DataEvent.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = DataEvent.Failure(error.message)
            }
        })
    }

    private fun getUserData() {
        progress.value = true

        val currentUser = FirebaseAuth.getInstance().currentUser
        val email: String? = currentUser?.email


        val shaEmail = sha256(email)
        Log.d("Sha Email", "sha : $shaEmail")
        dbUser.child(shaEmail).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(userSnapshot in snapshot.children){
                    when(userSnapshot.key) {
                        "jenis_kelamin" -> {
                            val genderVal = userSnapshot.getValue(String::class.java)

                            if(genderVal != "" && genderVal != null){
                                dataState.value = dataState.value.copy(
                                    gender = genderVal
                                )
                            }
                        }
                        "warna_kulit" -> {
                            val skinVal = userSnapshot.getValue(String::class.java)

                            if(skinVal != "" && skinVal != null){
                                dataState.value = dataState.value.copy(
                                    warna_kulit = skinVal
                                )
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error Profile View Model", error.message)
            }
        })
        progress.value = false
    }

    private fun fetchAllDataFromFirebase(){
        val tempList = mutableListOf<Pakaian>()
        response.value = DataEvent.Loading

        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnap in snapshot.children) {
                    val pakaianItem = dataSnap.getValue(Pakaian::class.java)
                    if(pakaianItem != null) {
                        tempList.add(pakaianItem)
                    }
                }
                response.value = DataEvent.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = DataEvent.Failure(error.message)
            }
        })
    }

    private fun fetchWomenDataFromFirebase(){
        val tempList = mutableListOf<Pakaian>()
        response.value = DataEvent.Loading

        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnap in snapshot.children) {
                    val pakaianItem = dataSnap.getValue(Pakaian::class.java)
                    if(pakaianItem != null && pakaianItem.gender == "wanita") {
                        tempList.add(pakaianItem)
                    }
                }
                response.value = DataEvent.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = DataEvent.Failure(error.message)
            }
        })
    }

    private fun fetchMenDataFromFirebase(){
        val tempList = mutableListOf<Pakaian>()
        response.value = DataEvent.Loading

        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnap in snapshot.children) {
                    val pakaianItem = dataSnap.getValue(Pakaian::class.java)
                    if(pakaianItem != null && pakaianItem.gender == "pria") {
                        tempList.add(pakaianItem)
                    }
                }
                response.value = DataEvent.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = DataEvent.Failure(error.message)
            }
        })
    }

    private fun fetchDataFromFirebaseByQuery(query: String){
        val tempList = mutableListOf<Pakaian>()
        response.value = DataEvent.Loading

        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnap in snapshot.children) {
                    val pakaianItem = dataSnap.getValue(Pakaian::class.java)
                    if(pakaianItem != null && pakaianItem.keyword == query) {
                        tempList.add(pakaianItem)
                    }
                }
                response.value = DataEvent.Success(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = DataEvent.Failure(error.message)
            }
        })
    }

    fun isFirstTimeHomeOpen(){
        getUserData()
        fetchForYouDataFromFirebase()
//        fetchAllDataFromFirebase()
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