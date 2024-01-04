package com.jiahaoliuliu.androidmvvmsample.di.component

import com.jiahaoliuliu.androidmvvmsample.di.ActivityScope
import com.jiahaoliuliu.androidmvvmsample.di.module.PresentationModule
import com.jiahaoliuliu.androidmvvmsample.presentation.main.view.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [PresentationModule::class])
interface PresentationComponent {

    fun inject(activity: TopHeadlineActivity)
}