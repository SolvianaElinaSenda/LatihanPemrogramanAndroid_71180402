package com.ilina.pertemuan10_71180402

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var db:SQLiteDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db=DatabaseHelper(this).writableDatabase
        val edtNama=findViewById<EditText>(R.id.edtNama)
        val edtUsia=findViewById<EditText>(R.id.edtUsia)
        val btnSimpan=findViewById<Button>(R.id.btnSimpan)
        val btnHapus=findViewById<Button>(R.id.btnHapus)
        val btnCari=findViewById<Button>(R.id.btnCari)

        btnSimpan.setOnClickListener() {
            val values =ContentValues().apply {
                put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA,edtNama.text.toString())
                put(DatabaseContract.Penduduk.COLUMN_NAME_USIA,edtUsia.text.toString())
            }
            db?.insert(DatabaseContract.Penduduk.TABLE_NAME,null,values)
            edtNama.setText("")
            edtUsia.setText("")
            refreshData()
        }
        btnHapus.setOnClickListener{
            val selection="nama=? OR usia=?"
            val selectionArgs= arrayOf(edtNama.text.toString(),
                edtUsia.text.toString())
            db?.delete(DatabaseContract.Penduduk.TABLE_NAME,selection,selectionArgs)

            edtNama.setText("")
            edtUsia.setText("")
            refreshData()
        }

        btnCari.setOnClickListener{
            val selection="nama=? OR usia=?"
            val selectionArgs= arrayOf(edtNama.text.toString(),
                edtUsia.text.toString())
//            db?.delete(DatabaseContract.Penduduk.TABLE_NAME,selection,selectionArgs)

            edtNama.setText("")
            edtUsia.setText("")
            refreshData()
        }

        refreshData()
    }


    fun refreshData(){
        val txvHasil=findViewById<TextView>(R.id.txvHasil)
        val colums= arrayOf(BaseColumns._ID,DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
                DatabaseContract.Penduduk.COLUMN_NAME_USIA)
        val cursor=db?.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            colums,
            null,
            null,
            null,
            null,
            null
        )
        var result=""
        with(cursor){
            while(this!!.moveToNext()){
                result += "${this!!.getString(1)}-${this!!.getString(2)}th\n"
            }
        }
        txvHasil.text=result
    }



    fun search(keyword: String){
        val txvHasil=findViewById<TextView>(R.id.txvHasil)
        val colums= arrayOf(BaseColumns._ID,DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA)
        val cursor=db?.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            colums,
            "nama=? OR usia=?",
            arrayOf(keyword,keyword),
            null,
            null,
            null
        )
        var result=""
        with(cursor){
            while(this!!.moveToNext()){
                result += "${this!!.getString(1)}-${this!!.getString(2)}th\n"
            }
        }
        txvHasil.text=result
    }

}