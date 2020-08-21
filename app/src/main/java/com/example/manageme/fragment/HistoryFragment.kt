package com.example.manageme.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.example.manageme.R
import com.example.manageme.activity.SettingActivity
import com.example.manageme.presenter.presentbase
import com.example.manageme.presenter.presenthistory
import com.example.manageme.presenter.presenthome
import kotlin.math.log

class HistoryFragment : Fragment(), presentbase, View.OnClickListener {
    private lateinit var p: presenthome
    private lateinit var tm: TextView
    private lateinit var tp: TextView
    private lateinit var tr: TextView
    private lateinit var cal_layout: LinearLayout
    private lateinit var bln: TextView
    private lateinit var txt_rata_info : TextView
    private lateinit var thn: TextView
    private lateinit var btn_set : Button
    private lateinit var btn_total : Button
    private lateinit var alertfragment: AlertCalendarFragment




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        totalcondition()
 /*       p.totalpemasukan(tm)
        p.totalpengeluaran(tp)
        p.rataratapengeluaran(tr)
       // bln.text = p.bulan
       // thn.text = p.tahun
        bln.text = "Bulan"
        thn.text = "Tahun"*/


    }

    fun totalcondition(){
        p.totalpemasukan(tm)
        p.totalpengeluaran(tp)
        p.ratatotalpengeluaran(tr)
       // p.rataratapengeluaran(tr)
        bln.text = "Bulan"
        thn.text = "Tahun"
        txt_rata_info.text = "Per Bulan"
    }


    override fun init(v: View) {
        tm = v.findViewById(R.id.total_pmskn)
        tp = v.findViewById(R.id.total_pglrn)
        tr = v.findViewById(R.id.txt_rata)
        bln = v.findViewById(R.id.txt_bulan)
        thn = v.findViewById(R.id.txt_tahun)
        txt_rata_info = v.findViewById(R.id.txt_rata_info)
        btn_set = v.findViewById(R.id.btn_setting)
        cal_layout = v.findViewById(R.id.cal_layout)
        btn_total = v.findViewById(R.id.btn_total)
        btn_total.setOnClickListener(this)
        cal_layout.setOnClickListener(this)
        btn_set.setOnClickListener(this)
        p = presenthome(context!!)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            cal_layout.id -> {

                alertfragment = AlertCalendarFragment(v.context, p.bulan, p.tahun, tr, p, bln, thn, tm, tp, btn_total,txt_rata_info)
                alertfragment.show()
            }
            btn_set.id ->{
                val inten = Intent(activity,SettingActivity::class.java)
                startActivity(inten)

            }
            btn_total.id -> {
                btn_total.visibility = View.GONE
                totalcondition()
            }
        }
    }


}
