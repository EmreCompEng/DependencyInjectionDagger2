package com.emreergun.dependencyinjectiondagger2.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.emreergun.dependencyinjectiondagger2.models.User
import com.emreergun.dependencyinjectiondagger2.network.auth.AuthApi
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    fun getUser(id: Int) {
        authApi.getUser(id)
            .subscribeOn(Schedulers.io())
            .subscribe(object :DisposableObserver<User>(){
                override fun onNext(user: User) {
                    Log.d(TAG, "onNext: ${user.name}")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: ${e.message}")
                    dispose()
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                    dispose()
                }


            })
    }


}