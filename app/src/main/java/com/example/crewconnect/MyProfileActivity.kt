package com.example.crewconnect

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.crewconnect.databinding.ActivityIntroBinding
import com.example.crewconnect.databinding.ActivityMyProfileBinding
import com.example.crewconnect.firebase.FirestoreClass
import com.example.crewconnect.models.user
import com.example.crewconnect.utils.Constants
import java.io.IOException
import java.util.jar.Manifest

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

        binding!!.navProfileImage.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                Constants. showImageChooser(this)
            }else{
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    Constants.READ_STORAGE_PERMISSION_CODE
                )
            }
        }



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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== Constants.READ_STORAGE_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Constants.showImageChooser(this)
            }
        }else{
            Toast.makeText(this, " you just denied permission for storage.", Toast.LENGTH_SHORT).show()

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK && requestCode==Constants.PICK_IMAGE_REQUEST_CODE && data!!.data!=null){
            mSelectedImageUri=data.data

            try {
                Glide
                    .with(this@MyProfileActivity)
                    .load(mSelectedImageUri)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_person_24)
                    .into(findViewById(R.id.nav_profile_image))
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }









}