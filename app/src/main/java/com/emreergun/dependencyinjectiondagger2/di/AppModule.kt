package com.emreergun.dependencyinjectiondagger2.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.emreergun.dependencyinjectiondagger2.R
import com.emreergun.dependencyinjectiondagger2.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// AppComponentte modül olarak ekle
// Projecin heryerinde kullanıcak provide modulleri buraya yazılır
@Module
class AppModule {

    // Dökümanlara göre verileri statik olarak tanımlamız öneriliyor !!!
    companion object {

        @Singleton
        @Provides
        fun provideRequestOpitons():RequestOptions{
            return RequestOptions
                .placeholderOf(R.drawable.ic_emre_icon)
                .error(R.drawable.ic_error_image)
        }

        // application ve requestOptions otomatik olarak enjeckte ediliyor
        // Sadece @Inject metodu ile App içinde istediğimiz yerde kullanabiliriz
        @Singleton
        @Provides
        fun provideGlideInstance(application: Application,requestOptions: RequestOptions):RequestManager{
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
        }

        @Singleton
        @Provides
        fun provideAppDrawable(application: Application): Drawable{
            return ContextCompat.getDrawable(application,R.drawable.ic_emre_icon)!!
        }

        @Singleton
        @Provides
        fun provideRetrofitInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Retrofit çağrışarını observable türüne dönüştürür
                .build()
        }

    }

}










