package com.example.crewconnect

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.Window
import android.view.WindowManager
import com.example.crewconnect.databinding.ActivityFrontPageBinding

class FrontPage : AppCompatActivity() {

   private var binding:ActivityFrontPageBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFrontPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val typeFace: Typeface = Typeface.createFromAsset(assets,"Prototype.otf")
        binding?.tvAppName?.typeface=typeFace
    }


}