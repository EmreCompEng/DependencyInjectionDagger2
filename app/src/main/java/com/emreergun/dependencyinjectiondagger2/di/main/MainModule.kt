package com.emreergun.dependencyinjectiondagger2.di.main

import com.emreergun.dependencyinjectiondagger2.network.main.MainApi
import com.emreergun.dependencyinjectiondagger2.ui.main.post.PostRecyclerViewAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    companion object{

        @MainScope
        @Provides
        fun provideAdapter(): PostRecyclerViewAdapter {
            return PostRecyclerViewAdapter()
            // Zaten 1 tane adapter olup içindeki liste değişeceği için
            // Sürekli adapter tanımlamasından gereksiz memory leak olmasından kaçınmak için
            // Neden single ton yapmayalım ki
            // :)
        }

        // Bütün providelerı MainScope'la
        @MainScope
        @Provides
        fun provideRetrofit(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }

    }

}