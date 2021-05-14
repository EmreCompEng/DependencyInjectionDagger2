package com.emreergun.dependencyinjectiondagger2.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.emreergun.dependencyinjectiondagger2.R
import com.emreergun.dependencyinjectiondagger2.viewmodels.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

// DaggerAppCompatActivity => AppCompatActivity'den bir farkı yoktur ve AppCompatActivity içindeki bütün metotları içerir
class AuthActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

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


        setLogo()

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