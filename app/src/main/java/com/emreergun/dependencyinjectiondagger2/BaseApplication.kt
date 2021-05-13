package com.emreergun.dependencyinjectiondagger2

import com.emreergun.dependencyinjectiondagger2.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication(){

    // App Component çağırır
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}


// DaggerApplication dagger android tarafından sağlanan yapıdır
// Daha kolay kod yazımı sağlar
// BaseApplication = client , AppComponent =Servis ,
// Genel olarak Componentler = Servis , Activities & Fragments = Clients