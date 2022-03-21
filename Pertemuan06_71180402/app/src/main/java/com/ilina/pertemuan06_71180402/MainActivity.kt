package com.ilina.pertemuan06_71180402

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentA = FragmentA()
        val fragmentB = FragmentB()
        val btnFrA: Button = findViewById(R.id.btnFrA)
        val btnFrB: Button = findViewById(R.id.btnFrB)
        val btnH1:Button=findViewById(R.id.btnH1)
        val button2:Button=findViewById(R.id.button2)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmen, fragmentA)
            commit()
        }
        btnFrA.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmen, fragmentA)
                commit()
            }
        }
        btnFrB.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmen, fragmentB)
                commit()
            }
        }
        btnH1.setOnClickListener {
            val i: Intent =Intent(this,HalamanDuaActivity::class.java)
            startActivity(i)
        }

        button2.setOnClickListener {
            val i: Intent =Intent(this,HalamanTigaActivity::class.java)
            startActivity(i)
        }
    }
}