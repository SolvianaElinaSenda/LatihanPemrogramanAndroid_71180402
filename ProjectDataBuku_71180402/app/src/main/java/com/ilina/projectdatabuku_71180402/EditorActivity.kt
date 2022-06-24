package com.ilina.projectdatabuku_71180402

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class EditorActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        ///Panggil databasenya
        firestore = FirebaseFirestore.getInstance()

        val judulBuku = findViewById<EditText>(R.id.judul_buku)
        val namaPenulis = findViewById<EditText>(R.id.nama_penulis)
        val namaPenerbit = findViewById<EditText>(R.id.nama_penerbit)
        val thnTerbit = findViewById<EditText>(R.id.thn_terbit)
        val jmlHalaman = findViewById<EditText>(R.id.jml_halaman)
        val btnSave = findViewById<Button>(R.id.btnSave)
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Menyimpan...")





        btnSave.setOnClickListener {
            if (judulBuku.length() > 0 &&
                namaPenulis.length() > 0 &&
                namaPenerbit.length() > 0 &&
                thnTerbit.length() > 0 &&
                jmlHalaman.length() > 0
            ) {
                val buku = Buku(
                    judulBuku.text.toString(),
                    namaPenulis.text.toString(),
                    namaPenerbit.text.toString(),
                    thnTerbit.text.toString().toInt(),
                    jmlHalaman.text.toString().toInt()
                )

                judulBuku.setText("")
                namaPenulis.setText("")
                namaPenerbit.setText("")
                thnTerbit.setText("")
                jmlHalaman.setText("")
                progressDialog.show()
                firestore?.collection("buku")?.add(buku)
                    ?.addOnSuccessListener {
                        Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT)
                            .show()
                            progressDialog.dismiss()
                    }?.addOnFailureListener {
//                        Log.d(TAG, "proses penyimpanan gagal")
                        Toast.makeText(applicationContext, "Data Gagal Disimpan", Toast.LENGTH_SHORT)
                            .show()
                        progressDialog.dismiss()
                    }

            } else {
                Toast.makeText(applicationContext, "Silahkan isi semua data", Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }
}