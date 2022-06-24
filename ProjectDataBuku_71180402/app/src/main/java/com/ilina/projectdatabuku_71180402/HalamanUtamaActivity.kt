package com.ilina.projectdatabuku_71180402

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HalamanUtamaActivity : AppCompatActivity() {
    private lateinit var  btn:Button
    var firestore : FirebaseFirestore?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halamanutama)
        firestore = FirebaseFirestore.getInstance()

        //Jika tomobl dengan id logout di klik,maka otomatis akan lagsung menuju ke Main Activity
        //atau halaman login
        btn=findViewById(R.id.logout)
        btn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val i= Intent(this,MainActivity::class.java)
            startActivity(i)
        }
        //jika tombol add dickik akan langsung menuju ke halaman editor
        val btnAdd=findViewById<FloatingActionButton>(R.id.btnadd)
        btnAdd.setOnClickListener {
            val i=Intent(this,EditorActivity::class.java)

            startActivity(i)
        }
        refreshData()


    }
    //ini untuk mengambil data
    //funtion ini berfungsi untuk mengambil data dari collection
    //dan datanya akan dimasukan ke dalam komponen dgan id txvHasil
    fun refreshData(){
        val txvHasil=findViewById<TextView>(R.id.txvHasil)
        firestore?.collection("buku")?.get()
            ?.addOnSuccessListener {
                    snapshot->
                var hasil=""
                for (doc in snapshot){
                    hasil+="${doc.get("judulBuku")}-${doc.get("namaPenulis")}-${doc.get("namaPenerbit")}-${doc.get("tahunTerbit")}-${doc.get("jumlahHalaman")} \n "

                }
                txvHasil.text=hasil

            }


    }
}