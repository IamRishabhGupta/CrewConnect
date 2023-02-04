package com.example.crewconnect
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.crewconnect.databinding.ActivityFrontPageBinding
import com.example.crewconnect.firebase.FirestoreClass

class FrontPage : AppCompatActivity() {

   private var binding:ActivityFrontPageBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFrontPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        val typeFace: Typeface = Typeface.createFromAsset(assets,"Prototype.ttf")
//        binding?.tvAppName?.typeface=typeFace

       Handler().postDelayed(




           {

               var currentUserID=FirestoreClass().getCurrentUserId()
               if(currentUserID.isNotEmpty())
               {
                   startActivity(Intent(this,MainActivity::class.java))

               }
               else
               {
                   startActivity(Intent(this,IntroActivity::class.java))

               }

       },2500)
    }


}