package com.emreergun.dependencyinjectiondagger2.di.main

import com.emreergun.dependencyinjectiondagger2.ui.main.post.PostFragment
import com.emreergun.dependencyinjectiondagger2.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    // Profile Fragment
    @ContributesAndroidInjector
    abstract fun contributeProfileFragment():ProfileFragment

    // Post Fragment
    @ContributesAndroidInjector
    abstract fun contributePostFragment():PostFragment



}