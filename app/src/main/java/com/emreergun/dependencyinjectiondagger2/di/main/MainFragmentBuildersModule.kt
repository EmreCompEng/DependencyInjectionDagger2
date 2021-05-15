package com.emreergun.dependencyinjectiondagger2.di.main

import com.emreergun.dependencyinjectiondagger2.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment():ProfileFragment



}