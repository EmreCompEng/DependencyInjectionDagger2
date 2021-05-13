package com.emreergun.dependencyinjectiondagger2.di

import android.app.Application
import com.emreergun.dependencyinjectiondagger2.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication>{


    @Component.Builder
    interface Builder{
        @BindsInstance //  @BindsInstance => metod build edilirken aynı anda data da build edilir
        fun application(application:Application):Builder

        fun build():AppComponent
    }


}


// Uygulama Boyunca kullanılacak instanceler , objeler appComponent seviyesindedir
// AndroidInjector<BaseApplication> ==> void inject(application Application) yazımını engeller daha kolaylık sağlar

// BaseApplication => Client , AppComponent=> Service gibi düşün

// @BindInstance
/*
    @Component.Builder
   interface Builder {
      @BindsInstance
      Builder userName(@UserName String userName);

      AppComponent build();
     ...
   }

 */