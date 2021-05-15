package com.emreergun.dependencyinjectiondagger2.di.main

import androidx.lifecycle.ViewModel
import com.emreergun.dependencyinjectiondagger2.di.viewmodels.ViewModelKey
import com.emreergun.dependencyinjectiondagger2.ui.auth.AuthViewModel
import com.emreergun.dependencyinjectiondagger2.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    // MainViewModelsModule
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindAuthViewModule(profileViewModel: ProfileViewModel): ViewModel
}