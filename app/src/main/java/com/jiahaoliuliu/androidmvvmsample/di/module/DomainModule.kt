package com.jiahaoliuliu.androidmvvmsample.di.module

import com.jiahaoliuliu.androidmvvmsample.data.api.NetworkService
import com.jiahaoliuliu.androidmvvmsample.data.mapper.TopHeadlineMapper
import com.jiahaoliuliu.androidmvvmsample.data.repository.TopHeadlineRepository
import com.jiahaoliuliu.androidmvvmsample.di.BaseUrl
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCase
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    fun provideRetrieveTopHeadlineUseCase(
        topHeadlineRepository: TopHeadlineRepository,
        topHeadlineMapper: TopHeadlineMapper
    ): RetrieveTopHeadlineUseCase = RetrieveTopHeadlineUseCaseImpl(
        topHeadlineRepository = topHeadlineRepository,
        topHeadlineMapper = topHeadlineMapper
    )
}