package com.emreergun.dependencyinjectiondagger2

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

// DaggerAppCompatActivity => AppCompatActivity'den bir farkı yoktur ve AppCompatActivity içindeki bütün metotları içerir
class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var logo:Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

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