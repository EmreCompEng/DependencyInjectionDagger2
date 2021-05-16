package com.emreergun.dependencyinjectiondagger2.network.auth

import com.emreergun.dependencyinjectiondagger2.models.user.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Single<User>
}