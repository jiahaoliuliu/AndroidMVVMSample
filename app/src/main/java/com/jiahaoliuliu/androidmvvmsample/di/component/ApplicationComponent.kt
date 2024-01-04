package com.jiahaoliuliu.androidmvvmsample.di.component

import android.content.Context
import com.jiahaoliuliu.androidmvvmsample.AndroidMVVMSampleApplication
import com.jiahaoliuliu.androidmvvmsample.data.api.NetworkService
import com.jiahaoliuliu.androidmvvmsample.data.repository.TopHeadlineRepository
import com.jiahaoliuliu.androidmvvmsample.di.ApplicationContext
import com.jiahaoliuliu.androidmvvmsample.di.module.ApplicationModule
import com.jiahaoliuliu.androidmvvmsample.di.module.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidMVVMSampleApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository
}