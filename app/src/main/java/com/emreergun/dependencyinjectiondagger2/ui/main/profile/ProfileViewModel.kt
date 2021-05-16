package com.emreergun.dependencyinjectiondagger2.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.emreergun.dependencyinjectiondagger2.SessionManager
import com.emreergun.dependencyinjectiondagger2.models.user.User
import com.emreergun.dependencyinjectiondagger2.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager) :ViewModel() {
    companion object {
        private const val TAG = "ProfileViewModel"
    }
    init {
        Log.d(TAG, "ProfileViewModel is ready...: ")
    }

    fun getAuthenticatedUser(): LiveData<AuthResource<User>> {
        return sessionManager.cachedUserLiveData
    }


}