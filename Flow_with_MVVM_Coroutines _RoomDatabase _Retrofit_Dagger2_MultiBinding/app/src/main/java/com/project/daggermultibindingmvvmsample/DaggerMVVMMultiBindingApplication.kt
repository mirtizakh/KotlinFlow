package com.project.daggermultibindingmvvmsample

import com.project.daggermultibindingmvvmsample.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DaggerMVVMMultiBindingApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).baseUrl("https://reqres.in/").build()

    }

}