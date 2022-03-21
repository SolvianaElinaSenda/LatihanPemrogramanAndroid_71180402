package com.ilina.pertemuan06_71180402

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HalamanTigaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halamantiga)

        val fragmentA = FragmentA()
        val fragmentC = FragmentC()
        val btnFrA: Button = findViewById(R.id.btnFrA)
        val btnFrC: Button = findViewById(R.id.btnFrC)
        val btnH1:Button=findViewById(R.id.btnH1)
        val button3:Button=findViewById(R.id.button3)

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
        btnFrC.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmen, fragmentC)
                commit()
            }

        }
        btnH1.setOnClickListener {
            val i: Intent = Intent(this,HalamanDuaActivity::class.java)
            startActivity(i)
        }

        button3.setOnClickListener {
            val i: Intent = Intent(this,HalamanTigaActivity::class.java)
            startActivity(i)
        }
    }
}
