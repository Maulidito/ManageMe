package com.example.manageme.fragment

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.manageme.R
import com.example.manageme.presenter.presenthistory
import com.example.manageme.presenter.presenthome
import com.example.manageme.view.viewalert
import com.example.manageme.view.viewdel
import java.util.*
import kotlin.collections.ArrayList

class AlertCalendarFragment(c: Context, bln: String, thn: String, tr: TextView, p: presenthome, txtbln: TextView, txtthn: TextView, masuk: TextView, keluar: TextView, btn_total : Button,rata_info : TextView) : viewalert, viewdel, View.OnClickListener {
    private val ad: AlertDialog.Builder
    private val minflate: LayoutInflater
    private var bln: String
    private var thn: String
    private val c: Context
    private var P: presenthome
    private lateinit var calendar: ArrayAdapter<String>
    private lateinit var alertcalendar: AlertDialog
    private lateinit var sp_bulan: Spinner
    private lateinit var sp_tahun: Spinner
    private lateinit var txt_tanggal: TextView
    private lateinit var done: Button
    private lateinit var del: Button
    private  var rata_info: TextView
    private var tr: TextView
    private var txtbln: TextView
    private var txtthn: TextView
    private var txtmasuk: TextView
    private var txtkeluar: TextView
    private var btn_total: Button
    private var phome : presenthome = p


    init {
        ad = AlertDialog.Builder(c)
        minflate = LayoutInflater.from(c)
        this.bln = bln
        this.thn = thn
        this.c = c
        this.tr = tr
        P = presenthome(this.c)
        this.txtbln = txtbln
        this.txtthn = txtthn
        txtmasuk = masuk
        txtkeluar = keluar
        this.btn_total = btn_total
        this.rata_info = rata_info
    }

    override fun oncreate() {
        var v = minflate.inflate(R.layout.calendaralert_fragment, null, false)
        ad.setView(v)
        sp_bulan = v.findViewById(R.id.sp_bulan)
        sp_tahun = v.findViewById(R.id.sp_tahun)
        txt_tanggal = v.findViewById(R.id.tv_tgl)
        done = v.findViewById(R.id.btn_done)
        del = v.findViewById(R.id.btn_delete)
        Log.v("bulan",bln)
        init()
        create_tahun()
        set_spinner(sp_bulan.count, sp_tahun.count)
        alertcalendar = ad.create()
        alertcalendar.setView(v)
    }

    fun show() {
        oncreate()
        alertcalendar.show()

    }

    override fun init() {
        inittxttanggal()
        calendar = ArrayAdapter(c, R.layout.support_simple_spinner_dropdown_item)
        sp_tahun.adapter = calendar
        del.setOnClickListener(this)
        done.setOnClickListener(this)
    }

    private fun create_tahun() {
        for (j in 2000..P.settahun().toInt()) {
            calendar.add(j.toString())
        }
    }

    private fun set_spinner(value_bulan: Int, value_tahun: Int) {
        if (btn_total.visibility == View.VISIBLE) {
            setselectionspinner(value_bulan,value_tahun,txtbln.text.toString(),txtthn.text.toString())

        }else{
            setselectionspinner(value_bulan,value_tahun,bln,thn)
        }
    }

    fun setselectionspinner(value_bulan: Int, value_tahun: Int, txt_tgl : String, txt_thn : String){
        for (pos in 0..value_bulan) {
            Log.v("value_bulan", txt_tgl)
            if (sp_bulan.getItemAtPosition(pos) ==  txt_tgl) {
                sp_bulan.setSelection(pos)
                break
            }
        }
        for (pos in 0..value_tahun) {
            if (sp_tahun.getItemAtPosition(pos) == txt_thn) {
                sp_tahun.setSelection(pos)
                break
            }
        }
    }

    private fun inittxttanggal() {
        if (btn_total.visibility == View.VISIBLE) {
            txt_tanggal.text =  txtbln.text.toString() + " / " + txtthn.text.toString()
        }else{
            txt_tanggal.text = "Bulan" + " / " + "Tahun"
        }

    }


    override fun onClick(v: View?) {
        when (v) {
            done -> {
                var arr: Array<String>
                bln = sp_bulan.selectedItem.toString()
                thn = sp_tahun.selectedItem.toString()
                txtbln.text = bln
                txtthn.text = thn
                inittxttanggal()
                arr = P.rataoptional(sp_bulan.selectedItem.toString(), sp_tahun.selectedItem.toString())
                txtkeluar.text = arr[0]
                txtmasuk.text = arr[1]
                tr.text = arr[2]
                btn_total.visibility = View.VISIBLE
                rata_info.text = "Per Hari"
                phome = P
            }
            del -> {

            }

        }
        alertcalendar.cancel()
    }


}