package com.ilina.pertemuan08_71180402

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar=findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             R.id.menu_notifikasi -> Toast.makeText(this, "Menu Notifikasi", Toast.LENGTH_SHORT).show()
             R.id.menu_setting -> Toast.makeText(this, "Menu Setings", Toast.LENGTH_SHORT).show()
             R.id.menu_profile -> Toast.makeText(this, "Menu Profile", Toast.LENGTH_SHORT).show()

         }
        return super.onOptionsItemSelected(item)
    }
}