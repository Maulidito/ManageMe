package com.example.manageme.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.manageme.model.Transaksi
import java.lang.ArithmeticException
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

val Database_name = "Mydatabase"

class Database(context : Context) : SQLiteOpenHelper(context, Database_name,null,9){
    private var Transaction : ArrayList<Transaksi>



    init {
        Transaction = Tranc
    }
    companion object{
        private val Table_name = "Transaksi"
        private val id = "id"
        private  val harga = "harga"
        private val jenis = "jenis"
        private val tipe = "tipe"
        private val tanggal = "tgl"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val create_table_transaksi =
                ("CREATE TABLE $Table_name ( " +
                        "$id INTEGER PRIMARY KEY, " +
                        "$jenis  TEXT NOT NULL, " +
                        "$tipe TEXT NOT NULL, " +
                        "$harga INTEGER NOT NULL, " +
                        "$tanggal  Timestamp DATE DEFAULT (datetime('now', 'LOCALTIME'))" +
                        ");")
        db!!.execSQL(create_table_transaksi)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

                var create_table_transaksi_old =
                        ("DROP TABLE IF EXISTS " + Table_name)

                db!!.execSQL(create_table_transaksi_old)
                Log.v("onuprade", "masuk")
                onCreate(db)


    }
    val Tranc:ArrayList<Transaksi>
        get() {


            val T = ArrayList<Transaksi>()
            val query = "SELECT * FROM $Table_name"
            val db: SQLiteDatabase = this.writableDatabase
            val cursor = db.rawQuery(query,null)
            if (cursor.moveToFirst()){
                do {

                    val Tr = Transaksi(
                            cursor.getInt(cursor.getColumnIndex(id)),
                            cursor.getInt(cursor.getColumnIndex(harga)),
                            cursor.getString(cursor.getColumnIndex(jenis)),
                            cursor.getString(cursor.getColumnIndex(tipe))
                    )

                    Tr.tanggal = time(cursor.getString(cursor.getColumnIndex(tanggal)))
                    //Log.v("Calendar",test1date.get(Calendar.MONTH).toString())
                    Log.v("calendar",cursor.getString((cursor.getColumnIndex(tanggal))).toString())
                    T.add(Tr)

                }while (cursor.moveToNext())

            }
            db.close()
            return T
        }

    private fun time (time : String) : String{
        val testdate : DateFormat = SimpleDateFormat("yyyy-MM-dd")
        return testdate.format(testdate.parse(time).time)
    }

        fun addtransaksi(T:Transaksi){
            val db = this.writableDatabase
            val values = ContentValues()
            if (Transaction.isEmpty()){
                values.put(id,1)
            }else{
                values.put(id,++Transaction.last().id)
            }

            values.put(jenis,T.jenis)
            values.put(harga,T.harga)
            values.put(tipe,T.tipe)
            db.insert(Table_name,null,values)
            db.close()
        }

    fun gettransaksi(): ArrayList<Transaksi> {
        return Transaction
    }




    fun totalpemasukan() : Int{
        var total : Int = 0
        Transaction.forEach {
            if (it.tipe.equals("Pemasukan")){total+=it.harga}
        }
        return total
    }
    fun totalpengeluaran() : Int{
        var total : Int = 0
        Transaction.forEach {
            if (it.tipe.equals("Pengeluaran")){total+=it.harga}
        }
        return total
    }

    fun totalratapengeluaran(bulan : String) : Int{
        var perbulan = 0
        Log.v("pengeluaran",bulan)
        Transaction.forEach {
            var tgl = it.tanggal?.split("-")
            if (tgl?.get(1)==bulan ){
                perbulan = tgl.get(1).toInt()
            }
        }
        return (totalpengeluaran()/perbulan)
    }

    fun ratapengeluaran(sort_bulan : String, sort_tahun : String) : Array<Int>{
        var total = 0
        var hari = 0
        var pemasukan = 0
        var excep = 0
        var list : Array<Int>
        try {

            Transaction.forEach {
                var tgl = it.tanggal?.split("-")
                Log.v("ratapengeluaran",tgl?.get(1).toString()  + " " + sort_bulan + " " + tgl?.get(0).toString() + " " + sort_tahun)
                if (sort_bulan == tgl?.get(1) && sort_tahun == tgl.get(0) ) {
                    if (it.tipe.equals("Pengeluaran")) {
                        total += it.harga
                        hari = tgl?.get(2).toInt()
                    }else{
                        pemasukan +=it.harga
                    }
                }

            }
          excep = (total/hari).toInt()

        }catch (e : ArithmeticException){

            excep = 0
        }
        list = arrayOf(total,pemasukan,excep)
        return list
    }

    fun delete(ID : Int){
        val db = this.writableDatabase
        db.delete(Table_name,"$id = ?", arrayOf(ID.toString()))
        Transaction = Tranc
        db.close()

    }



}