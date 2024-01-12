package com.jiahaoliuliu.androidmvvmsample.di.component

import com.jiahaoliuliu.androidmvvmsample.AndroidMVVMSampleApplication
import com.jiahaoliuliu.androidmvvmsample.di.module.DataModule
import com.jiahaoliuliu.androidmvvmsample.di.module.DomainModule
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidMVVMSampleApplication)

    fun getRetrieveTopHeadlineUseCase(): RetrieveTopHeadlineUseCase
}