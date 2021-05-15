package com.emreergun.dependencyinjectiondagger2.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.emreergun.dependencyinjectiondagger2.SessionManager
import com.emreergun.dependencyinjectiondagger2.models.User
import com.emreergun.dependencyinjectiondagger2.network.auth.AuthApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    // Cache' olan Live Data döndürürlür
    fun observeAuthState():LiveData<AuthResource<User>>{
        return sessionManager.cachedUserLiveData
    }

    fun authenticateWithId(id:Int){
        sessionManager.cachedUserLiveData.value=AuthResource.Loading() // İlk başta veri boş durumda
        authApi.getUser(id)
            .observeOn(AndroidSchedulers.mainThread()) // Eğer Main thread de işlem yapmassak , updateleri ui tarafında gösteremeyiz
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSingleObserver<User>(){
                override fun onSuccess(user: User) {
                    Log.d(TAG, "onNext: ${user.name}")
                    sessionManager.cachedUserLiveData.value=AuthResource.Authenticated(user)
                }
                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: $e")
                    sessionManager.cachedUserLiveData.value=AuthResource.Error("Hata")
                }
            })

    }


}