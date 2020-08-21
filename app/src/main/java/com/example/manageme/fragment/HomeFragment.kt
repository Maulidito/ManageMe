package com.example.manageme.fragment

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import com.example.manageme.R
import com.example.manageme.activity.AddActivity
import com.example.manageme.adapter.homeadpater
import kotlin.math.log

class HomeFragment() : Fragment(), AdapterView.OnItemClickListener, View.OnClickListener {
    lateinit var alert: AlertFragment
    lateinit var listview: ListView
    lateinit var homeadpater: homeadpater
    private lateinit var btn_add: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeadpater = homeadpater(view.context)
        listview = view.findViewById(R.id.list)
        listview.adapter = homeadpater
        btn_add = view.findViewById(R.id.btn_add)
        btn_add.setOnClickListener(this)
        listview.setOnItemClickListener(this)


    }



    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        alert = AlertFragment(this.context!!, parent!!.getItemAtPosition(position), listview,this.activity!!)

        alert.show()


    }

    override fun onClick(v: View?) {
        val item_id = v?.id
        when (item_id) {
            btn_add.id -> {
                val inten = Intent(v.context, AddActivity::class.java)
                startActivityForResult(inten, 10)
                activity!!.overridePendingTransition(R.anim.slide_down_in, R.anim.slide_down_out)

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode.equals(10)) {
            recreate(this.activity!!)
        }else if (resultCode.equals(11)){

        }
    }


}



