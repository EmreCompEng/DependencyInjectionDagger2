package com.emreergun.dependencyinjectiondagger2.di.main

import androidx.lifecycle.ViewModel
import com.emreergun.dependencyinjectiondagger2.di.viewmodels.ViewModelKey
import com.emreergun.dependencyinjectiondagger2.ui.auth.AuthViewModel
import com.emreergun.dependencyinjectiondagger2.ui.main.post.PostViewModel
import com.emreergun.dependencyinjectiondagger2.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    // MainViewModelsModule

    // ProfileViewModel
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModule(profileViewModel: ProfileViewModel): ViewModel

    // PostViewModel
    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModule(postViewModel: PostViewModel): ViewModel



}