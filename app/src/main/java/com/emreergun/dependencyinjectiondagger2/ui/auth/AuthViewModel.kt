package com.emreergun.dependencyinjectiondagger2.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.emreergun.dependencyinjectiondagger2.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi:AuthApi) :ViewModel()  {
    init {
        Log.d(Companion.TAG, "AuthViewMdoel working... ")
        if (authApi==null){
            Log.d(TAG, "AuthViewModel : auth api is null")
        }else{
            Log.d(TAG, "AuthViewModel : auth api is not null")
        }
    }

    companion object {
        private const val TAG = "AuthViewModel"
    }
}