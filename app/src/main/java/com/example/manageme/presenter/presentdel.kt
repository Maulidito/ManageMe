package com.example.manageme.presenter

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.example.manageme.database.Database
import com.example.manageme.model.Transaksi

class presentdel(context: Context) : Nominal {
    private val db: Database
    lateinit var T: Transaksi

    init {
        db = Database(context)
    }

    fun deleteTranskasi(id: Int) {
        db.delete(id)
    }

    fun getTransaksi(id: Any) {
        T = id as Transaksi


    }

    fun settextview(masuk : TextView, keluar : TextView, rata : TextView, bln : String, thn : String){
            masuk.text = NumberTextchanget(db.totalpemasukan().toString())
            keluar.text = NumberTextchanget(db.totalpengeluaran().toString())
            var dbrata = db.ratapengeluaran(bln,thn)
            rata.text = NumberTextchanget(dbrata[2].toString())


    }


}