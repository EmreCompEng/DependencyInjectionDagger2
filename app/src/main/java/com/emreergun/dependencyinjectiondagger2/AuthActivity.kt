package com.emreergun.dependencyinjectiondagger2

import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

// DaggerAppCompatActivity => AppCompatActivity'den bir farkı yoktur ve AppCompatActivity içindeki bütün metotları içerir
class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var someString:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d(TAG, "onCreate: $someString")

    }

    companion object {
        private const val TAG = "AuthActivity"
    }
}