package com.jiahaoliuliu.androidmvvmsample.di.component

import android.content.Context
import com.jiahaoliuliu.androidmvvmsample.AndroidMVVMSampleApplication
import com.jiahaoliuliu.androidmvvmsample.data.api.NetworkService
import com.jiahaoliuliu.androidmvvmsample.data.mapper.TopHeadlineMapper
import com.jiahaoliuliu.androidmvvmsample.data.repository.TopHeadlineRepository
import com.jiahaoliuliu.androidmvvmsample.di.ApplicationContext
import com.jiahaoliuliu.androidmvvmsample.di.module.ApplicationModule
import com.jiahaoliuliu.androidmvvmsample.di.module.DataModule
import com.jiahaoliuliu.androidmvvmsample.di.module.DomainModule
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class, DomainModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidMVVMSampleApplication)

    fun getRetrieveTopHeadlineUseCase(): RetrieveTopHeadlineUseCase
}