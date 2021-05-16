package com.emreergun.dependencyinjectiondagger2.di.main

import com.emreergun.dependencyinjectiondagger2.network.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    companion object{

        // Bütün providelerı MainScope'la
        @MainScope
        @Provides
        fun provideRetrofit(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }

    }

}