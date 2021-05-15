package com.emreergun.dependencyinjectiondagger2.di.auth

import androidx.lifecycle.ViewModel
import com.emreergun.dependencyinjectiondagger2.di.viewmodels.ViewModelKey
import com.emreergun.dependencyinjectiondagger2.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    // AuthViewModule
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModule(authViewModel: AuthViewModel):ViewModel


}