package com.example.crewconnect.firebase

import android.util.Log
import com.example.crewconnect.SignInActivity
import com.example.crewconnect.SignUpActivity
import com.example.crewconnect.models.user
import com.example.crewconnect.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User

class FirestoreClass {

    private val mFireStore=FirebaseFirestore.getInstance()

    fun registerUser(activity:SignUpActivity,userinfo:user){
        mFireStore.collection(Constants.USERS).document(getCurrentUserId()).set(userinfo,
            SetOptions.merge()).addOnSuccessListener {
            activity.userRegisteredSuccess()
        }.addOnFailureListener{
                e->
            Log.e(activity.javaClass.simpleName,"Error")
        }
    }

    fun signInUser(activity: SignInActivity)
    {
        mFireStore.collection(Constants.USERS)
             .document(getCurrentUserId()).get().addOnSuccessListener {document->
           val loggedInUser=document.toObject(User::class.java)!!
            if(loggedInUser!=null)
            {
                activity.signInSuccess(loggedInUser)
            }

        }.addOnFailureListener{
                e->
            Log.e("FirestoreSignInuser","error in writing document ")
        }
    }



     fun getCurrentUserId():String{

        var currentUser=FirebaseAuth.getInstance().currentUser
        var currentUserId=""
        if(currentUser!=null){
            currentUserId=currentUser.uid
        }
        return currentUserId
    }

}