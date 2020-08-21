package com.example.manageme.presenter

import android.app.AlarmManager
import android.content.Context
import android.widget.CheckBox
import android.widget.TextView
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.time.milliseconds

class AlarmManager(c : Context) {
    val c = c
    var alarmManager : AlarmManager
    var timenow : Long
    val Calendar : Calendar = java.util.Calendar.getInstance()

    init {
        alarmManager = c.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        timenow = Calendar.timeInMillis
    }

    fun setalarm(cb1 : CheckBox, cb2 : CheckBox, cb3 : CheckBox){
        var time1 = converttime(cb1.text.toString())
        //var alarm = AlarmManager.AlarmClockInfo(time1,)
       // alarmManager.setAlarmClock()
    }

    fun converttime(text : String) : Long{
        var list : List<String>
        list = text.split(":")
        var hour = list[0].toLong()
        var minute = list[1].toLong()
        return TimeUnit.HOURS.toMillis(hour) + TimeUnit.MINUTES.toMillis(minute)

    }


}