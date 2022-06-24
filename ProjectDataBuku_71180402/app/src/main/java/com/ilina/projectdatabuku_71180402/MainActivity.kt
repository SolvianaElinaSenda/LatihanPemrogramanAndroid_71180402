package com.ilina.projectdatabuku_71180402

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.ilina.projectdatabuku_71180402.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    private companion object{
        private const val RC_SIGN_IN=100
        private const val TAG="GOOGLE_SIGN_IN_TAG"


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //konfigurasi google signi

        val googleSignInOptions= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient= GoogleSignIn.getClient(this, googleSignInOptions)

        //init firebaseAuth
        firebaseAuth= FirebaseAuth.getInstance()
        checkUser()

        //google signin Button,klik
        binding.login.setOnClickListener {
            Log.d(TAG,"onCreate: begin Google SigIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }
    }

    private fun checkUser() {
        //check if user logged in or not
        val firebaseUser=firebaseAuth.currentUser
        if (firebaseUser != null){
            //user is already login
            //start HalamanUtama activity
            startActivity(Intent(this@MainActivity,HalamanUtamaActivity::class.java))
            finish()
            finish()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                super.onActivityResult(requestCode, resultCode, data)
                if(requestCode== RC_SIGN_IN){
                    Log.d(TAG,"onActivityResult: Google SignIn intent result")
                    val accountTask=GoogleSignIn.getSignedInAccountFromIntent(data)
                    try {
                        //google signin Success,new auth with firebase
                        val account=accountTask.getResult(ApiException::class.java)
                        firebaseAuthWithGoogleAccount(account)


                    }catch (e:Exception){
                        //failed google signIn
                        Log.d(TAG,"onActivityResult: ${e.message}")
                    }

            }
        }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG,"firebaseAuthWithGoogleAccount: begin firebase auth with google account ")

        val credential= GoogleAuthProvider.getCredential(account!!.idToken,null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                //login suksees
                Log.d(TAG, "firebaseAuthWithGoogleAccount: LoggedIn")
                //get loggedInuser
                val firebaseUser = firebaseAuth.currentUser
                //get user info
                val uid = firebaseUser!!.uid
                val email = firebaseUser!!.email
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Email: $email")

                //check if user is new or existing
                if(authResult.additionalUserInfo!!.isNewUser){
                    Log.d(TAG,"firebaseAuthWithGoogleAccount: Account created...\n$email")
                    Toast.makeText(this@MainActivity,"Account created...\n$email",Toast.LENGTH_SHORT).show()


                } else{
                    Log.d(TAG,"firebaseAuthWithGoogleAccount: Existing user...\n$email")
                    Toast.makeText(this@MainActivity,"LoggedIn...\n$email",Toast.LENGTH_SHORT).show()

                }
                //start profile activity
                startActivity(Intent(this@MainActivity,HalamanUtamaActivity::class.java))
                finish()

            }.addOnFailureListener{e->
            Log.d(TAG,"firebaseAuthWithGoogleAccount: Loggin Failed due to ${e.message}")
            Toast.makeText(this@MainActivity,"Loggin Failed due to ${e.message}",Toast.LENGTH_SHORT).show()

        }
    }

    }



