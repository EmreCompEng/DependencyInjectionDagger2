package com.emreergun.dependencyinjectiondagger2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.emreergun.dependencyinjectiondagger2.ui.auth.AuthActivity
import com.emreergun.dependencyinjectiondagger2.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity :DaggerAppCompatActivity(){

    companion object {
        private const val TAG = "BaseActivity"
    }

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        sessionManager.cachedUserLiveData
            .observe(this,{userAuthResource->
                if (userAuthResource != null) {
                    when (userAuthResource.status) {
                        AuthResource.AuthStatus.LOADING -> {
                            Log.d(TAG, "subscribeObservers: LOGIN SUCCESS :${userAuthResource.data?.email}")
                        }
                        AuthResource.AuthStatus.AUTHENTICATED -> {

                        }
                        AuthResource.AuthStatus.ERROR -> {
                            Log.d(TAG, "subscribeObservers: ${userAuthResource.message}")
                        }
                        AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                            navLoginScreen()
                        }

                    }
                }
            })
    }

    private fun navLoginScreen(){
        val intent=Intent(this,AuthActivity::class.java)
        startActivity(intent)
        finish()
    }




}