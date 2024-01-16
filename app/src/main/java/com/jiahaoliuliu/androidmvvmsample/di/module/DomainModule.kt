package com.jiahaoliuliu.androidmvvmsample.di.module

import com.jiahaoliuliu.androidmvvmsample.data.repository.TopHeadlineRepositoryImpl
import com.jiahaoliuliu.androidmvvmsample.domain.repository.TopHeadlineRepository
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCase
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindRetrieveTopHeadlineUseCase(impl: RetrieveTopHeadlineUseCaseImpl): RetrieveTopHeadlineUseCase

    @Binds
    @Singleton
    abstract fun bindTopHeadlineRepository(impl: TopHeadlineRepositoryImpl): TopHeadlineRepository
}