package com.example.facerecog

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val button1= findViewById<Button>(R.id.button_click1)
        button1.setOnClickListener {
            val intent = Intent(this, ActivityNewface::class.java)
            startActivity(intent)
        }

            val button2= findViewById<Button>(R.id.button_click2)
            button2.setOnClickListener {
                val intent= Intent(this, ActivityAttend::class.java)
                startActivity(intent)
        }



    }
}