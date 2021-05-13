package com.emreergun.dependencyinjectiondagger2.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.emreergun.dependencyinjectiondagger2.R
import dagger.Module
import dagger.Provides

// AppComponentte modül olarak ekle
@Module
class AppModule {

    // Dökümanlara göre verileri statik olarak tanımlamız öneriliyor !!!
    companion object {

        @Provides
        fun provideRequestOpitons():RequestOptions{
            return RequestOptions
                .placeholderOf(R.drawable.ic_emre_icon)
                .error(R.drawable.ic_error_image)
        }

        // application ve requestOptions otomatik olarak enjeckte ediliyor
        // Sadece @Inject metodu ile App içinde istediğimiz yerde kullanabiliriz
        @Provides
        fun provideGlideInstance(application: Application,requestOptions: RequestOptions):RequestManager{
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
        }

        @Provides
        fun provideAppDrawable(application: Application): Drawable{
            return ContextCompat.getDrawable(application,R.drawable.ic_emre_icon)!!
        }

    }

}