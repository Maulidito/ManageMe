package com.example.manageme.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.manageme.R
import com.example.manageme.adapter.homeadpater
import com.example.manageme.presenter.presentadd
import com.example.manageme.presenter.presentbase
import com.example.manageme.view.viewadd
import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*



class AddActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener,viewadd,presentbase {
    private lateinit var P : presentadd
    private lateinit var  btn_selesai : Button
    private lateinit var rbgroup : RadioGroup
    private lateinit var rbgroup1 : RadioGroup
    private lateinit var rbgroup2 : RadioGroup
    private lateinit var rb : RadioButton
    private lateinit var harga : EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add)

        addbuton()
        init(harga.rootView)
        rbgroup1.setOnCheckedChangeListener(this)
        rbgroup2.setOnCheckedChangeListener(this)
        btn_selesai.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
       when(v){
           btn_selesai -> {
               var bool : Boolean = true
               if (harga.text.isEmpty()){
                   harga.error = "Masukan Harga"
                   bool = false
               }
               if (rbgroup1.checkedRadioButtonId == -1 && rbgroup2.checkedRadioButtonId == -1){
                   Toast.makeText(this,"Lengkapkan Data ",Toast.LENGTH_SHORT).show()
                   bool = false
               }
               if (bool){

                   var selecetedbutton : String? = null
                   if (rbgroup1.checkedRadioButtonId == -1){
                       rb = findViewById(rbgroup2.checkedRadioButtonId)
                       selecetedbutton = rb.text.toString()
                   }else{
                       rb = findViewById(rbgroup1.checkedRadioButtonId)
                       selecetedbutton = rb.text.toString()
                   }
                   rb = findViewById(rbgroup.checkedRadioButtonId)
                   try {
                       P.addtransaksi(harga.text.toString().toInt(),selecetedbutton,rb.text.toString())
                       onaddresult("Data telah Dimasukan")
                   }catch (e : NumberFormatException){
                       onaddresult("error "+ e)
                   }
                   setResult(10)
                   finish()
                   this.overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out)
                   var intent = Intent(v.context,HomeActivity::class.java)
                   startActivity(intent)

               }
           }


       }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when(group) {
            rbgroup1 -> {
                if(rbgroup2.checkedRadioButtonId.toString() != "-1"){
                    super.onResume()
                    rbgroup2.setOnCheckedChangeListener(null)
                    rbgroup2.clearCheck()
                    rbgroup2.setOnCheckedChangeListener(this)
                }

            }
            rbgroup2 -> {
                if(rbgroup1.checkedRadioButtonId.toString() != "-1"){
                    super.onResume()
                    rbgroup1.setOnCheckedChangeListener(null)
                    rbgroup1.clearCheck()
                    rbgroup1.setOnCheckedChangeListener(this)

                }

            }
        }
    }

    override fun onaddresult(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    override fun init(v: View) {
        P = presentadd(v)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(R.anim.slide_up_in,R.anim.slide_up_out)
        setResult(11)
        finish()
    }

    private fun addbuton(){
        rbgroup = findViewById<RadioGroup>(R.id.radio_group_category)
        rbgroup1 = findViewById<RadioGroup>(R.id.radio_detail_category1)
        rbgroup2 = findViewById<RadioGroup>(R.id.radio_detail_category2)
        btn_selesai = findViewById<Button>(R.id.btn_selesai)
        harga = findViewById(R.id.add_harga)
    }



}
