package com.emreergun.dependencyinjectiondagger2.di.auth

import com.emreergun.dependencyinjectiondagger2.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    companion object{
        @AuthScope
        @Provides
        fun provideAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }
    }
}