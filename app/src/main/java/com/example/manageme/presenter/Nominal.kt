package com.example.manageme.presenter

import java.text.NumberFormat
import java.util.*

interface Nominal {
    fun NumberTextchanget(t: String): String {
        var LocaleID = Locale("in", "ID")
        var rupiah: NumberFormat = NumberFormat.getCurrencyInstance(LocaleID)
        return rupiah.format(t.toInt())
    }
}