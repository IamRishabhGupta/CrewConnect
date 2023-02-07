package com.example.crewconnect

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.crewconnect.databinding.ActivityIntroBinding
import com.example.crewconnect.databinding.ActivityMyProfileBinding
import com.example.crewconnect.firebase.FirestoreClass
import com.example.crewconnect.models.user

class MyProfileActivity : BaseActivity() {
    private var binding:ActivityMyProfileBinding?=null
    private var mProfileImageURL:String?=null
    private lateinit var mUserDetails: user
    private var mSelectedImageUri: Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupActionBar()

        FirestoreClass().loadUserData(this)
    }

    private fun setupActionBar(){
        setSupportActionBar(binding!!.toolbarProfileActivity)

        val actionBar=supportActionBar
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        binding!!.toolbarProfileActivity.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun setUserDataInUI(user:user)
    {
        Glide
            .with(this@MyProfileActivity)
            .load(user.image)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_person_24)
            .into(findViewById(R.id.nav_profile_image))


        binding!!.etEmailProfile.setText(user.email)
        binding!!.etNameProfile.setText(user.name)
        if(user.mobile!=0L){
            binding!!.etMobileProfile.setText(user.mobile.toString())
        }
    }





}