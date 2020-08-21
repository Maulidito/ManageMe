package com.example.manageme.presenter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.manageme.database.Database
import com.example.manageme.model.Transaksi
import java.util.*

class presenthome(context : Context): Nominal{
    var db : Database
    lateinit var T: Transaksi
    var bulan: String
    var tahun: String
    var Cal: Calendar

    init {
        db = Database(context)
        Cal = Calendar.getInstance()
        bulan = "0" +(Cal.get(Calendar.MONTH) + 1).toString()
        tahun = Cal.get(Calendar.YEAR).toString()

    }
    fun initpresenthome(view : View){
        Cal = Calendar.getInstance()
        db = Database(view!!.context)
        bulan = "0" +(Cal.get(Calendar.MONTH) + 1).toString()
        tahun = Cal.get(Calendar.YEAR).toString()
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

    fun totalpengeluaran(t: TextView) {

        t.text = NumberTextchanget(db.totalpengeluaran().toString())

    }
    fun totalpemasukan(t: TextView) {

        t.text = NumberTextchanget(db.totalpemasukan().toString())
    }
    fun ratatotalpengeluaran(t: TextView) {
        t.text = NumberTextchanget(db.totalratapengeluaran(setbulan()).toString())
    }
    fun rataratapengeluaran(t: TextView) {
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
    fun rataoptional(bln: String, thn: String): Array<String> {
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