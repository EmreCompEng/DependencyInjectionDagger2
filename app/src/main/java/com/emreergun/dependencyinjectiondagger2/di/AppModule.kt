package com.emreergun.dependencyinjectiondagger2.di

import android.app.Application
import dagger.Module
import dagger.Provides

// AppComponentte modül olarak ekle
@Module
class AppModule {

    // Dökümanlara göre verileri statik olarak tanımlamız öneriliyor !!!
    companion object {

        @Provides
        fun testString(): String {
            return "Love You!!!!"
        }

        @Provides
        fun getApp(application: Application): Boolean {
            // Daima false döner çünkü application AppComponent'ta Bind ediliyor
            // Ve oradaki application datası buraya enjekte ediliyor
            // Ve böylece metod çalışmış oluyor
            return false
        }


    }

}