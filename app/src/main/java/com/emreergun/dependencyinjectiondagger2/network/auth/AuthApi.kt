package com.emreergun.dependencyinjectiondagger2.network.auth

import com.emreergun.dependencyinjectiondagger2.models.User
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Observable<User>
}