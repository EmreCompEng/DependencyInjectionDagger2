package com.emreergun.dependencyinjectiondagger2.di

import com.emreergun.dependencyinjectiondagger2.AuthActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule { // Activitler için toplu modüldür , inject edilecek activityler burada belirtilir

    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity  // AuthActivity @inject ile verileri çekebilir ,Bunun Tanımı yapıldı


}

// inject edilecek activity de inject() metodunu kullanmamıza gerek kulamaz
// contributeAuthActivity yerine herhangi metod ismi de yazılabilirdi farketmiyor , önemli olan dönüş tipi