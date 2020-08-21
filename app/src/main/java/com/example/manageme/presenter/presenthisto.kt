package com.example.manageme.presenter

import android.widget.TextView

interface presenthisto {
    fun totalpengeluaran(t : TextView)
    fun totalpemasukan(t : TextView)
    fun rataratapengeluaran(t : TextView)
    fun ratatotalpengeluaran(t : TextView)
    fun rataoptional(bln : String, thn : String) : Array<String>
}