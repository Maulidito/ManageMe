package com.example.manageme.activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import com.example.manageme.R
import com.example.manageme.fragment.AlertReset
import com.example.manageme.fragment.AlertTimePicker
import com.example.manageme.presenter.PushNotification
import com.example.manageme.presenter.Settings
import com.example.manageme.presenter.presentbase
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity(), View.OnClickListener, presentbase, Settings {
    private lateinit var btn_back: Button
    private lateinit var btn_waktu1: Button
    private lateinit var btn_waktu2: Button
    private lateinit var btn_waktu3: Button
    private lateinit var cb_notif: CheckBox
    private lateinit var cb_waktu1: CheckBox
    private lateinit var cb_waktu2: CheckBox
    private lateinit var cb_waktu3: CheckBox
    private lateinit var btn_reset: Button
    private lateinit var SharedPreferences: SharedPreferences
    private lateinit var AlertTimePicker: AlertTimePicker
    private lateinit var AlertReset: AlertReset
    private lateinit var PushNotification : PushNotification


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        init(this.view)
        loadsetting()
        PushNotification.createnotificationchannel()
        btn_back.setOnClickListener(this)
        cb_notif.setOnClickListener(this)
        btn_waktu1.setOnClickListener(this)
        btn_waktu2.setOnClickListener(this)
        btn_waktu3.setOnClickListener(this)
        btn_reset.setOnClickListener(this)

    }

    override fun init(v: View) {
        btn_back = findViewById(R.id.btn_back)
        btn_waktu1 = findViewById(R.id.btn_waktu1)
        btn_waktu2 = findViewById(R.id.btn_waktu2)
        btn_waktu3 = findViewById(R.id.btn_waktu3)
        cb_waktu1 = findViewById(R.id.cb_waktu1)
        cb_waktu2 = findViewById(R.id.cb_waktu2)
        cb_waktu3 = findViewById(R.id.cb_waktu3)
        cb_notif = findViewById(R.id.cb_notifikasi)
        btn_reset = findViewById(R.id.btn_reset)
        PushNotification = PushNotification(this)
        SharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            btn_back.id -> {
                savesetting()
                finish()
            }
            cb_notif.id -> {
                initcheckbutton()
               // PushNotification.shownotification()


            }
            btn_waktu1.id -> {
                AlertTimePicker = AlertTimePicker(cb_waktu1)
                AlertTimePicker.show(supportFragmentManager, "timePicker")
            }
            btn_waktu2.id -> {
                AlertTimePicker = AlertTimePicker(cb_waktu2)
                AlertTimePicker.show(supportFragmentManager, "timePicker")
            }
            btn_waktu3.id -> {
                AlertTimePicker = AlertTimePicker(cb_waktu3)
                AlertTimePicker.show(supportFragmentManager, "timePicker")
            }
            btn_reset.id -> {
                AlertReset = AlertReset(v.context)
                AlertReset.show(supportFragmentManager, "alertreset")


            }

        }


    }

    fun initcheckbutton() {
        if (cb_notif.isChecked.equals(false)) {
            btn_waktu1.isEnabled = false
            btn_waktu2.isEnabled = false
            btn_waktu3.isEnabled = false
            cb_waktu1.isEnabled = false
            cb_waktu2.isEnabled = false
            cb_waktu3.isEnabled = false

        } else {
            btn_waktu1.isEnabled = true
            btn_waktu2.isEnabled = true
            btn_waktu3.isEnabled = true
            cb_waktu1.isEnabled = true
            cb_waktu2.isEnabled = true
            cb_waktu3.isEnabled = true
        }
    }

    override fun savesetting() {
        var edit: SharedPreferences.Editor = SharedPreferences.edit()
        edit.putBoolean("NOTIF", cb_notif.isChecked)
        edit.putBoolean("CB_NOTIF1", cb_waktu1.isChecked)
        edit.putBoolean("CB_NOTIF2", cb_waktu2.isChecked)
        edit.putBoolean("CB_NOTIF3", cb_waktu3.isChecked)
        edit.putString("CB_NOTIF_TEXT1", cb_waktu1.text.toString())
        edit.putString("CB_NOTIF_TEXT2", cb_waktu2.text.toString())
        edit.putString("CB_NOTIF_TEXT3", cb_waktu3.text.toString())
        edit.apply()


    }

    override fun loadsetting() {
        cb_notif.isChecked = SharedPreferences.getBoolean("NOTIF", false)
        cb_waktu1.isChecked = SharedPreferences.getBoolean("CB_NOTIF1", false)
        cb_waktu2.isChecked = SharedPreferences.getBoolean("CB_NOTIF2", false)
        cb_waktu3.isChecked = SharedPreferences.getBoolean("CB_NOTIF3", false)
        cb_waktu1.text = SharedPreferences.getString("CB_NOTIF_TEXT1", cb_waktu1.text.toString())
        cb_waktu2.text = SharedPreferences.getString("CB_NOTIF_TEXT2", cb_waktu1.text.toString())
        cb_waktu3.text = SharedPreferences.getString("CB_NOTIF_TEXT3", cb_waktu1.text.toString())
        initcheckbutton()
    }


}