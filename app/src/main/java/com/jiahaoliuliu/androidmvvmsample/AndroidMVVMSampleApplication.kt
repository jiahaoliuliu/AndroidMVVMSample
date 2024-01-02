package com.jiahaoliuliu.androidmvvmsample

import android.app.Application
import com.jiahaoliuliu.androidmvvmsample.di.component.ApplicationComponent
import com.jiahaoliuliu.androidmvvmsample.di.component.DaggerApplicationComponent
import com.jiahaoliuliu.androidmvvmsample.di.module.ApplicationModule

class AndroidMVVMSampleApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}