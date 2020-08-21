package com.example.manageme.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.manageme.R
import com.example.manageme.activity.HomeActivity
import com.example.manageme.database.Database
import com.example.manageme.model.Transaksi
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class homeadpater( context: Context) : BaseAdapter() {
    private var C : Context
    private val minflator : LayoutInflater
    private var arr : ArrayList<Transaksi>
    private var db : Database

    init {
        C = context
        db = Database(C)
        minflator = LayoutInflater.from(context)
        arr = db.gettransaksi()
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view : View?
        val vh : ListRowHolder

        if (convertView == null){
            if (arr[position].tipe.equals("Pemasukan")) {
                view = this.minflator.inflate(R.layout.item_list, parent, false)
            }else{
                view = this.minflator.inflate(R.layout.item_list_red, parent, false)
            }
            vh = ListRowHolder(view)
            view.tag = vh
        }else{
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.harga.text =NumberTextchanget(arr.get(position).harga)
        vh.tgl.text = arr.get(position).tanggal
        return view
    }

    override fun getItem(position: Int): Any {
        return arr.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arr.size
    }

    private class ListRowHolder(row : View?){
         val harga : TextView
         val tgl : TextView
        init {
            this.harga = row?.findViewById(R.id.value) as TextView
            this.tgl = row?.findViewById(R.id.tgl) as TextView

        }
    }
    fun NumberTextchanget (t : Int) : String {
        var LocaleID = Locale("in","ID")
        var rupiah : NumberFormat = NumberFormat.getCurrencyInstance(LocaleID)
        return rupiah.format(t)
    }


    override fun notifyDataSetChanged() {
        db = Database(C)
        arr = db.gettransaksi()
    }


}


