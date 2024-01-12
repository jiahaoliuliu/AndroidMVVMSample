package com.jiahaoliuliu.androidmvvmsample.di.module

import com.jiahaoliuliu.androidmvvmsample.data.mapper.TopHeadlineMapper
import com.jiahaoliuliu.androidmvvmsample.data.repository.TopHeadlineRepository
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCase
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
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