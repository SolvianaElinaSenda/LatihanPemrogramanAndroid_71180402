package com.ilina.pertemuan11_71180402

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    var firestore : FirebaseFirestore?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()

        val edtNama=findViewById<EditText>(R.id.edtNama)
        val edtNim=findViewById<EditText>(R.id.edtNim)
        val edtIpk=findViewById<EditText>(R.id.edtIpk)
        val btnSimpan=findViewById<Button>(R.id.btnSimpan)
        val btnCari=findViewById<Button>(R.id.btnCari)
        val txvOutput=findViewById<TextView>(R.id.txvOutput)

        btnSimpan.setOnClickListener {
            val mahasiswa=Mahasiswa(edtNama.text.toString(),edtNim.text.toString(),edtIpk.text.toString().toDouble())
            edtNama.setText("")
            edtNim.setText("")
            edtIpk.setText("")
            firestore?.collection("mahasiswa")?.add(mahasiswa)
        }
        btnCari.setOnClickListener {
            firestore?.collection("mahasiswa")?.get()!!
                .addOnSuccessListener{ docs->
                    var output=""
                    for (doc in docs){
                        output+="\n${doc.data}"
                    }
                    txvOutput.setText(output)
                }
        }
    }
}