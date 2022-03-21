package com.ilina.pertemuan06_71180402

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HalamanDuaActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halamandua)

        val fragmentB = FragmentB()
        val fragmentC = FragmentC()
        val btnFrB: Button = findViewById(R.id.btnFrB)
        val btnFrC: Button = findViewById(R.id.btnFrC)
        val button3:Button=findViewById(R.id.button3)
        val button2:Button=findViewById(R.id.button2)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmen, fragmentB)
            commit()
        }
        btnFrB.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmen, fragmentB)
                commit()
            }
        }
        btnFrC.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmen, fragmentC)
                commit()
            }

        }

        button2.setOnClickListener {
            val i: Intent = Intent(this,HalamanDuaActivity::class.java)
            startActivity(i)
        }

        button3.setOnClickListener {
            val i: Intent = Intent(this,HalamanTigaActivity::class.java)
            startActivity(i)
        }
    }
    }
