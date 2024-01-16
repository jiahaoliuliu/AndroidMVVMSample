package com.jiahaoliuliu.androidmvvmsample.di.module

import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCase
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindRetrieveTopHeadlineUseCase(impl: RetrieveTopHeadlineUseCaseImpl): RetrieveTopHeadlineUseCase
}