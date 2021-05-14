package com.emreergun.dependencyinjectiondagger2.di.viewmodels

import androidx.lifecycle.ViewModelProvider
import com.emreergun.dependencyinjectiondagger2.di.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory):ViewModelProvider.Factory

}