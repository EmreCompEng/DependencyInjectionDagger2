package com.emreergun.dependencyinjectiondagger2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.emreergun.dependencyinjectiondagger2.models.user.User
import com.emreergun.dependencyinjectiondagger2.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton // Bütün uygulama boyunca kullanılacağı için AppComponent'e eklemeyi unutma!!!!
class SessionManager @Inject constructor() {
    companion object {
        private const val TAG = "SessionManager"
    }

    // Cache User Live Data
    val cachedUserLiveData=MutableLiveData<AuthResource<User>>() // AuthResource Live Data

    fun logOut(){
        Log.d(TAG, "logOut: User LogOut Correctly")
        cachedUserLiveData.value=AuthResource.Logout() // LogOut
    }
}