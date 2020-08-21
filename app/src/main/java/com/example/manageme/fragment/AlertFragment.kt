package com.example.manageme.fragment

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.manageme.R
import com.example.manageme.activity.HomeActivity
import com.example.manageme.adapter.homeadpater
import com.example.manageme.presenter.*
import com.example.manageme.view.viewdel
import java.lang.Exception
import java.lang.NumberFormatException

class AlertFragment(context: Context, id: Any, list: ListView, activity : Activity) : View.OnClickListener, viewdel, Nominal {
    private var builder: AlertDialog.Builder
    private val minfalter: LayoutInflater
    private lateinit var title: TextView
    private lateinit var harga: TextView
    private lateinit var tanggal: TextView
    private lateinit var jenis: TextView
    private lateinit var done: Button
    private lateinit var del: Button
    private lateinit var alert: AlertDialog
    private var activity: Activity
    private var adpater: homeadpater?
    private var list: ListView
    private val presentdel: presenthome
    private val id: Any

    init {
        builder = AlertDialog.Builder(context)
        minfalter = LayoutInflater.from(context)
        presentdel = presenthome(context)
        this.id = id
        this.adpater = null
        this.list = list
        this.activity = activity


    }

    fun oncreatebuilder() {
        var v: View = minfalter.inflate(R.layout.alert_fragment, null, false)
        builder.setView(v)
        title = v.findViewById(R.id.tv_title)
        harga = v.findViewById(R.id.tv_harga)
        tanggal = v.findViewById(R.id.tv_tanggal)
        jenis = v.findViewById(R.id.tv_jenis)
        done = v.findViewById(R.id.btn_done)
        del = v.findViewById(R.id.btn_delete)
        alert = builder.create()
        alert.create()
        init()

        done.setOnClickListener(this)
        del.setOnClickListener(this)


    }

    fun show() {
        oncreatebuilder()
        alert.show()
    }

    override fun onClick(v: View?) {
        var id = v!!.id
        when (id) {
            done.id -> {
            }
            del.id -> {
                try {


                presentdel.deleteTranskasi(presentdel.T.id)
                adpater = homeadpater(v.context)
                list.adapter = this.adpater
                Toast.makeText(v.context, "Delete Success", Toast.LENGTH_LONG).show()}
                catch (e : Exception){
                    Toast.makeText(v.context, "Delete failed", Toast.LENGTH_LONG).show()
                }
            }

        }

        alert.cancel()

    }

    override fun init() {
        presentdel.getTransaksi(id)
        title.text = presentdel.T.tipe
        harga.text = NumberTextchanget(presentdel.T.harga.toString())
        tanggal.text = presentdel.T.tanggal
        jenis.text = presentdel.T.jenis
    }


}