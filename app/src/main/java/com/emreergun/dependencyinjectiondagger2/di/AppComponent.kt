package com.emreergun.dependencyinjectiondagger2.di

import android.app.Application
import com.emreergun.dependencyinjectiondagger2.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

// Bütün Omurga burada
// Uygulama boyunca kontrolde olan component burasıdır
// AppComponentta kullanılacak moduller eklenir

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {


    @Component.Builder
    interface Builder {
        @BindsInstance //  @BindsInstance => metod build edilirken aynı anda data da build edilir
        fun application(application: Application): Builder
        fun build(): AppComponent
    }


}

// BaseApplication = client , AppComponent =Servis ,
// Genel olarak Componentler = Servis , Activities & Fragments = Clients


// Uygulama Boyunca kullanılacak instanceler , objeler appComponent seviyesindedir
// AndroidInjector<BaseApplication> ==> void inject(application Application) yazımını engeller daha kolaylık sağlar


// @BindInstance
/*
    @Component.Builder
   interface Builder {
      @BindsInstance Builder userName(@UserName String userName);

      AppComponent build();
     ...
   }

 */