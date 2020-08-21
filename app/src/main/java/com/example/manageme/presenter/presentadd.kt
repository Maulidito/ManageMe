package com.example.manageme.presenter

import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.example.manageme.database.Database
import com.example.manageme.model.Transaksi
import com.example.manageme.view.viewbase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class presentadd(view: View) {
    private val db : Database
    private lateinit var T : Transaksi

    init {
        db = Database(view.context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addtransaksi(a : Int, b : String, c : String){
        T = Transaksi(a,b,c)
        db.addtransaksi(T)
    }




}