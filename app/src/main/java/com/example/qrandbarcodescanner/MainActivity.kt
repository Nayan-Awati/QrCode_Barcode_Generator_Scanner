package com.example.qrandbarcodescanner

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var textt : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textt = this.findViewById(R.id.textDisplay)
        generate.setOnClickListener{
            startActivity(Intent(this, GenerateQRCode::class.java))

        }

        scan.setOnClickListener{
            startActivity(Intent(this, ScanQRcodeAndBarcode::class.java))
        }

        switchs.setOnCheckedChangeListener{ compoundButton, onSwitch->
            if (switchs.isChecked) {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchs.text = "Disable dark mode"

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchs.text = "Enable dark mode"
            }

        }

    }



    fun replaceFragment(fragment: Fragment, istransition:Boolean){
        val fragmentTransition = supportFragmentManager.beginTransaction()

        if (istransition){
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.add(R.id.mainScreen,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragments = supportFragmentManager.fragments
        if (fragments.size == 0){
            finish()
        }
    }
}