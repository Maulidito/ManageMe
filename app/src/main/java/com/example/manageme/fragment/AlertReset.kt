package com.example.manageme.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException
import java.util.concurrent.Delayed

class AlertReset(c: Context) : DialogFragment(), OnClickListener {
    private val c = c
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Memulai Dari Awal")
                    .setNegativeButton("TIDAK", this)
                    .setPositiveButton("IYA", this)
            builder.create()
        } ?: throw IllegalStateException("Cant Null")
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {

        when (which) {
            -1 -> {
                deletepreferences()
                recreate()
            }
            -2 -> {

            }

        }
        dialog!!.dismiss()
    }

    fun deletepreferences() {
        var SharedPreferences: SharedPreferences = c.getSharedPreferences("PREFS", MODE_PRIVATE)
        Log.v("reset", SharedPreferences.getBoolean("CB_NOTIF1", false).toString())
        var edit: SharedPreferences.Editor = SharedPreferences.edit()
        edit.clear()
        edit.apply()
    }

    fun recreate() {
        var intent = activity!!.intent
        activity!!.finish()
        startActivity(intent)
    }
}