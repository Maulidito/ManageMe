package com.example.manageme.presenter

import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.manageme.database.Database
import java.text.NumberFormat
import java.util.*
import kotlin.coroutines.coroutineContext

class presenthistory(view: View?) : presenthisto, Nominal {
    private val db: Database
    private val Cal: Calendar
    var bulan: String
    var tahun: String

    init {
        db = Database(view!!.context)
        Cal = Calendar.getInstance()
        bulan = "0" +(Cal.get(Calendar.MONTH) + 1).toString()
        tahun = Cal.get(Calendar.YEAR).toString()
    }

    override fun totalpengeluaran(t: TextView) {

        t.text = NumberTextchanget(db.totalpengeluaran().toString())

    }

    override fun totalpemasukan(t: TextView) {

        t.text = NumberTextchanget(db.totalpemasukan().toString())
    }
    override fun ratatotalpengeluaran(t: TextView) {
        t.text = NumberTextchanget(db.totalratapengeluaran(setbulan()).toString())
    }


    override fun rataratapengeluaran(t: TextView) {
        var rata: Array<Int>



        rata = db.ratapengeluaran(setbulan(), tahun)

        t.text = NumberTextchanget(rata[2].toString())

    }

    fun setbulan() : String{
        if ((Cal.get(Calendar.MONTH) + 1 < 10) && (bulan.split("0").count() != 2)) {
            bulan = "0" + bulan

        }

        return bulan
    }


    override fun rataoptional(bln: String, thn: String): Array<String> {
        var arr: Array<Int> = emptyArray()
        var arrstring: Array<String> = emptyArray()
        bulan = bln
        tahun = thn
        arr = db.ratapengeluaran(bulan, tahun)
        arrstring = arrayOf(NumberTextchanget(arr[0].toString()), NumberTextchanget(arr[1].toString()), NumberTextchanget(arr[2].toString()))
        return arrstring

    }

    fun settahun(): String {
        return Cal.get(Calendar.YEAR).toString()
    }


}