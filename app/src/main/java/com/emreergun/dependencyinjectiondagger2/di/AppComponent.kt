package com.emreergun.dependencyinjectiondagger2.di

import android.app.Application
import com.emreergun.dependencyinjectiondagger2.BaseApplication
import com.emreergun.dependencyinjectiondagger2.SessionManager
import com.emreergun.dependencyinjectiondagger2.di.viewmodels.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

// Bütün Omurga burada
// Uygulama boyunca kontrolde olan component burasıdır
// AppComponentta kullanılacak moduller eklenir

@Singleton // Application boyunca tek sefer üretilip sürekli bu instance kullanılacak moduller buraya yazılır
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager():SessionManager // Uygulama boyunca tek sefer oluşturulur heryerden ulaşılabilmesi sağlanr

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
