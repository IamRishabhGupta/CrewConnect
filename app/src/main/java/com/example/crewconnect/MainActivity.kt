package com.example.crewconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.example.crewconnect.databinding.ActivityMainBinding
import com.example.crewconnect.firebase.FirestoreClass
import com.example.crewconnect.models.user

import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User

class MainActivity : BaseActivity(),NavigationView.OnNavigationItemSelectedListener {
    private var binding: ActivityMainBinding? = null
    private var toolbar_main: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        toolbar_main = findViewById(R.id.toolbar_main_activity)
        setupActionBar()
        toolbar_main!!.setNavigationOnClickListener {
            toggleDrawer()
        }
        binding!!.navView.setNavigationItemSelectedListener(this)

        FirestoreClass().signInUser(this)

    }


    override fun onBackPressed() {
        if (binding!!.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding!!.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            doubleBackToExit()
        }
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_main)
        toolbar_main!!.setNavigationIcon(R.drawable.ic_baseline_format_align_justify_24)
    }

    private fun toggleDrawer() {
        if (binding!!.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding!!.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding!!.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_my_profile -> {
                Toast.makeText(this, "good", Toast.LENGTH_LONG).show()
            }

            R.id.nav_sign_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
        binding!!.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun updateNavigationUserDetails(user: user) {
        Glide
            .with(this)
            .load(user.image)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_person_24)
            .into(findViewById(R.id.nav_user_image))
        findViewById<TextView>(R.id.tv_username).text=user.name
    }
}


