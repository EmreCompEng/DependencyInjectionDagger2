package com.emreergun.dependencyinjectiondagger2.di

import com.emreergun.dependencyinjectiondagger2.di.auth.AuthModule
import com.emreergun.dependencyinjectiondagger2.di.auth.AuthViewModelModule
import com.emreergun.dependencyinjectiondagger2.ui.auth.AuthActivity
import com.emreergun.dependencyinjectiondagger2.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule { // Activitler için toplu modüldür , inject edilecek activityler burada belirtilir

    // AuthActivity de mullanılacak moduller eklenir
    @ContributesAndroidInjector(
        modules = [AuthViewModelModule::class ,AuthModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity  // AuthActivity @inject ile verileri çekebilir ,Bunun Tanımı yapıldı

    @ContributesAndroidInjector
    abstract fun mainActivity():MainActivity


}

// inject edilecek activity de inject() metodunu kullanmamıza gerek kulamaz
// contributeAuthActivity yerine herhangi metod ismi de yazılabilirdi farketmiyor , önemli olan dönüş tipi