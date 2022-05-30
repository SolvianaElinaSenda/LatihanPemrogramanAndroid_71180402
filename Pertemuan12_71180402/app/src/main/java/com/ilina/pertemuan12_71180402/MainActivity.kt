package com.ilina.pertemuan12_71180402

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var queue: RequestQueue?=null
    var BASE_URL="https://api.openweathermap.org/data/2.5/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queue= Volley.newRequestQueue(this)

        val edtKota=findViewById<EditText>(R.id.edtKota)
        val btnCek=findViewById<Button>(R.id.cekCuaca)
        val txvHasil=findViewById<TextView>(R.id.txvHasil)

        btnCek.setOnClickListener {
            cekCuaca(edtKota.text.toString())
        }
    }
    fun cekCuaca(kota: String){
        val url="${BASE_URL}weather?q=${kota}&appid=e644b22eb2fe41000c10cfc70bbdb30b"
        val request=StringRequest(
            Request.Method.GET,
            url,
            Response.Listener { response ->
                val txvHasil=findViewById<TextView>(R.id.txvHasil)
                val data=JSONObject(response)
                val deskripsi=data.getJSONArray("weather").getJSONObject(0).getString("description")
                val temperatur=data.getJSONObject("main").getDouble("temp")
                txvHasil.text="Cuaca: ${deskripsi} (${String.format("%.2f",temperatur-273.15).toDouble()}\u2103)"
            },
            Response.ErrorListener {

            }

            )
        queue?.add(request)

    }
}