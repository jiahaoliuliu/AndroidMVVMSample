package com.jiahaoliuliu.androidmvvmsample.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jiahaoliuliu.androidmvvmsample.di.ActivityContext
import com.jiahaoliuliu.androidmvvmsample.domain.usecase.RetrieveTopHeadlineUseCase
import com.jiahaoliuliu.androidmvvmsample.presentation.base.ViewModelProviderFactory
import com.jiahaoliuliu.androidmvvmsample.presentation.main.viewmodel.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideTopHeadlineViewModel(
        retrieveTopHeadlineUseCase: RetrieveTopHeadlineUseCase): TopHeadlineViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(TopHeadlineViewModel::class) {
            TopHeadlineViewModel(retrieveTopHeadlineUseCase)
        })[TopHeadlineViewModel::class.java]
    }
}