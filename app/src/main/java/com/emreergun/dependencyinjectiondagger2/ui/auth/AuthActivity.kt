package com.emreergun.dependencyinjectiondagger2.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
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
    private lateinit var progresBar: ProgressBar

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var provideViewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initViews()


        loginButton.setOnClickListener {
            if (!userEditText.text.isNullOrEmpty()) { // Eğer User Id Boş değil ise
                val userID = userEditText.text.toString().toInt()
                viewModel.authenticateWithId(userID)
            } else {  // Eğer User Id Boş ise
                Log.d(TAG, "onCreate: userEditText is null or empty")
            }
        }


        setLogo()
        subscribeObservers() // User Live Data dinlenmeye alındı , Her türlü değişimde dinlenilecek
    }

    // User Live Datasındaki updateleri dinle
    // Bütün durumlar bu şekilde kontrol edildi
    // Bütün yapıları bu şekilde tutumaya çalış :)
    private fun subscribeObservers() {
        viewModel.observeUser().observe(this, { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    AuthResource.AuthStatus.LOADING -> {
                        showProgressBar(true)
                        Log.d(
                            TAG,
                            "subscribeObservers: LOGIN SUCCESS :${userAuthResource.data?.email}"
                        )
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(
                            this,
                            userAuthResource.message + "\n 1 ile 10 arasında sayı giriniz",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                    AuthResource.AuthStatus.COMPLETED -> {
                        Log.d(TAG, "subscribeObservers: COMPLETED...")
                        showProgressBar(false)
                    }
                }
            }
        })
    }

    private fun showProgressBar(isShow: Boolean) {
        progresBar.isVisible = isShow
    }
    private fun initViews() {
        viewModel = ViewModelProvider(this, provideViewModelFactory).get(AuthViewModel::class.java)
        userEditText = findViewById(R.id.editTextTextUserId)
        loginButton = findViewById(R.id.buttonLogin)
        progresBar = findViewById(R.id.progressBar)
    }
    private fun setLogo() {
        requestManager
            .load(logo)
            .into((findViewById(R.id.imageView)))
    }
    companion object {
        private const val TAG = "AuthActivity"
    }
}