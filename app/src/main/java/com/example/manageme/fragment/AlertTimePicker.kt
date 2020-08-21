package com.example.manageme.fragment

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import android.text.format.DateFormat
import android.util.Log
import android.widget.TextView

import java.util.*

class AlertTimePicker(textview : TextView) : DialogFragment(),TimePickerDialog.OnTimeSetListener {
    private var result : String = ""
    private var tv : TextView = textview
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        c.timeZone = TimeZone.getDefault()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity,this,hour,minute,DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        tv.text = convert(hourOfDay) + " : " + convert(minute)
    }

    fun convert(time : Int) : String{
        if (time < 10){
            return "0"+time.toString()
        }else{
            return time.toString()
        }
    }





}