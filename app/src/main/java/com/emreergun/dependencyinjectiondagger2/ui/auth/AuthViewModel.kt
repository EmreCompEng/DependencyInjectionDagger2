package com.emreergun.dependencyinjectiondagger2.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.emreergun.dependencyinjectiondagger2.models.User
import com.emreergun.dependencyinjectiondagger2.network.auth.AuthApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }


    private val userLiveData=MutableLiveData<AuthResource<User>>() // Live Data
    fun observeUser():LiveData<AuthResource<User>>{
        return userLiveData
    }
    fun authenticateWithId(id:Int){
        userLiveData.value=AuthResource.Loading() // Data is null for loading first...
        authApi.getUserTestObservable(id)
            .observeOn(AndroidSchedulers.mainThread()) // Eğer Main thread de işlem yapmassak , updateleri ui tarafında gösteremeyiz
            .subscribeOn(Schedulers.io())
            .subscribe(object :DisposableSingleObserver<User>(){
                override fun onSuccess(user: User) {
                    Log.d(TAG, "onNext: ${user.name}")
                    userLiveData.value=AuthResource.Authenticated(user)
                }
                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: $e")
                    userLiveData.value=AuthResource.Error("Hata")
                }
            })

    }

}