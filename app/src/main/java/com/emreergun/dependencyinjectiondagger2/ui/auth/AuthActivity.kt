package com.emreergun.dependencyinjectiondagger2.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.emreergun.dependencyinjectiondagger2.R
import com.emreergun.dependencyinjectiondagger2.di.viewmodels.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

// DaggerAppCompatActivity => AppCompatActivity'den bir farkı yoktur ve AppCompatActivity içindeki bütün metotları içerir
class AuthActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var userEditText: EditText
    private lateinit var loginButton: Button

    @Inject
    lateinit var logo:Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var provideViewModelFactory: ViewModelFactory



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel=ViewModelProvider(this,provideViewModelFactory).get(AuthViewModel::class.java)
        userEditText=findViewById(R.id.editTextTextUserId)
        loginButton=findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {
            // Eğer User Id Boş değil ise
            if (!userEditText.text.isNullOrEmpty()){
                val userID=userEditText.text.toString().toInt()
                Log.d(TAG, "onCreate: $userID")
                viewModel.authenticateWithId(userID)
            }
            // Eğer User Id Boş ise
            else{
                Log.d(TAG, "onCreate: userEditText is null or empty")
            }
        }


        setLogo()
        subscribeObservers() // User Live Data dinlenmeye alındı , Her türlü değişimde dinlenilecek
    }

    // User Live Datasındaki updateleri dinle
    private fun subscribeObservers(){
        viewModel.observeUser().observeForever {user->
            if (user!=null){
                Log.d(TAG, "subscribeObservers: ${user.email}")
            }
        }
    }

    fun setLogo(){
        requestManager
            .load(logo)
            .into((findViewById(R.id.imageView)))
    }


    companion object {
        private const val TAG = "AuthActivity"
    }
}