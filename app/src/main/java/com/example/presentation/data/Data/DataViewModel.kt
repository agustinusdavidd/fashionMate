package com.example.presentation.data.Data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.presentation.utils.Models.Pakaian
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataViewModel : ViewModel(){

    val response: MutableState<DataEvent> = mutableStateOf(DataEvent.Empty)
    var progress = mutableStateOf(false)
    private val db = FirebaseDatabase.getInstance().getReference("pakaian")

    fun onEvent(event : DataEvent){
        when(event) {
            is DataEvent.filterForYou -> {
                fetchAllDataFromFirebase()
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
        fetchAllDataFromFirebase()
    }
}