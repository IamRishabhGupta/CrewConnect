package com.example.crewconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.crewconnect.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private var binding: ActivitySignUpBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
//        setupActionBar()
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

    }

    private fun setupActionBar(){
//        setSupportActionBar(binding?.toolbarSignupActivity)
//        val actionBar=supportActionBar
//        if(actionBar!=null){
//            actionBar.setDisplayHomeAsUpEnabled(true)
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
//        }
//        binding?.toolbarSignupActivity?.setNavigationOnClickListener{
//            onBackPressed()
//        }

//        binding?.btnSignup?.setOnClickListener {
//            registerUser()
//        }

    }
}