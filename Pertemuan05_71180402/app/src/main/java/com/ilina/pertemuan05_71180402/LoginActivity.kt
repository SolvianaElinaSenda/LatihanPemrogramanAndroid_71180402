package com.ilina.pertemuan05_71180402

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtUsername=findViewById<EditText>(R.id.username)
        val edtPassword=findViewById<EditText>(R.id.password)
        val btnLogin=findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            login(edtUsername.text.toString(),edtPassword.text.toString())

        }
    }
    fun login(username:String,password:String){
        if (password.equals("091199")){
            val i:Intent= Intent(this,MainActivity::class.java)
            i.putExtra("user",username)
            startActivity(i)
        }else{
            toast("Password yang Anda Masukan salah!! ")
        }


    }
    fun toast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}