package com.emreergun.dependencyinjectiondagger2.ui.auth

import androidx.lifecycle.*
import com.emreergun.dependencyinjectiondagger2.models.User
import com.emreergun.dependencyinjectiondagger2.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    private val userLiveData = MediatorLiveData<User>()

    // Flowable to live data
    fun authenticateWithId(id: Int) {
        val source: LiveData<User> =
            LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(id).subscribeOn(Schedulers.io())
            )
        userLiveData.addSource(source) { user ->
            userLiveData.value = user
            userLiveData.removeSource(source)
        }
    }

    // User Modeli ile bütün updatelerde dinlenildiği yere bilgi verir
    fun observeUser(): LiveData<User> {
        return userLiveData
    }

}