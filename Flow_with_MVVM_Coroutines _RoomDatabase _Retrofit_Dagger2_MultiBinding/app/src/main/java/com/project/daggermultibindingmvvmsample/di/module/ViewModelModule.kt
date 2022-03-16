package com.project.daggermultibindingmvvmsample.di.module

import androidx.lifecycle.ViewModel
import com.project.daggermultibindingmvvmsample.activity.main.MainActivityViewModel
import com.project.daggermultibindingmvvmsample.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(mainActivityViewModel: MainActivityViewModel) : ViewModel
}