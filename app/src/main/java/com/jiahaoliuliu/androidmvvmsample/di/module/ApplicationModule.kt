package com.jiahaoliuliu.androidmvvmsample.di.module

import android.content.Context
import com.jiahaoliuliu.androidmvvmsample.AndroidMVVMSampleApplication
import com.jiahaoliuliu.androidmvvmsample.data.api.NetworkService
import com.jiahaoliuliu.androidmvvmsample.di.ApplicationContext
import com.jiahaoliuliu.androidmvvmsample.di.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidMVVMSampleApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }
}