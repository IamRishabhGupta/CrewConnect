package com.example.crewconnect
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.crewconnect.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private var binding: ActivityIntroBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding!!.btnSignup.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        binding!!.btnSignin.setOnClickListener(
            {
                startActivity(Intent(this,SignInActivity::class.java))
            }
        )

    }
}