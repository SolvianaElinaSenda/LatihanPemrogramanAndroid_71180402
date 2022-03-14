package com.ilina.pertemuan05_71180402

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user=intent.getStringExtra("user")
        val salam=findViewById<TextView>(R.id.salam)
        salam.text="Selamat Datang ${user}"
    }
}



