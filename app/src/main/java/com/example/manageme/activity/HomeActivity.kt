package com.example.manageme.activity

import android.content.Context

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.manageme.R
import com.example.manageme.fragment.HistoryFragment
import com.example.manageme.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_home2.*

class HomeActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    private val fragmentmanager = supportFragmentManager
    private val fragment_home = HomeFragment()
    private val fragment_history = HistoryFragment()
    lateinit var fragmentTransaction : FragmentTransaction
    lateinit var btn_home: RadioButton
    lateinit var btn_histo: RadioButton
    lateinit var rg_home: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        btn_home = findViewById(R.id.rb_home)
        btn_histo = findViewById(R.id.rb_history)
        rg_home = findViewById(R.id.rg_home)
        rg_home.setOnCheckedChangeListener(this)
        fragmentTransaction = fragmentmanager.beginTransaction()
        fragmentTransaction.add(R.id.myFragment,fragment_home,"HOME")
        fragmentTransaction.add(R.id.myFragment,fragment_history,"HISTO")
        fragmentTransaction.show(fragment_home)
        fragmentTransaction.hide(fragment_history)
        fragmentTransaction.commit()


    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val item_id = checkedId

        fragmentTransaction = fragmentmanager.beginTransaction()
        when (item_id) {
            R.id.rb_home -> {

               fragmentTransaction.show(fragment_home)
                fragmentTransaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out)
                fragmentTransaction.attach(fragment_home)
                fragmentTransaction.detach(fragment_history)

              ///  fragmentTransaction.hide(fragment_history)

            }
            R.id.rb_history -> {


                fragmentTransaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out, R.anim.slide_right_in, R.anim.slide_right_out)
                fragmentTransaction.attach(fragment_history)
                fragmentTransaction.detach(fragment_home)
                fragmentTransaction.show(fragment_history)

                //fragmentTransaction.hide(fragment_home)
                }

        }
        fragmentTransaction.commit()


            }




    override fun onBackPressed() {
        if (fragmentmanager.findFragmentByTag("HISTO")!!.isHidden.equals(false)) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out)
            fragmentTransaction.show(fragment_home)
            fragmentTransaction.hide(fragment_history)
            rg_home.check(btn_home.id)
        } else {
            finish()
        }

    }
}









