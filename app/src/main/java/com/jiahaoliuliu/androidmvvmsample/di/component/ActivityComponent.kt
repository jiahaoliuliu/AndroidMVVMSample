package com.jiahaoliuliu.androidmvvmsample.di.component

import com.jiahaoliuliu.androidmvvmsample.di.ActivityScope
import com.jiahaoliuliu.androidmvvmsample.di.module.ActivityModule
import com.jiahaoliuliu.androidmvvmsample.presentation.main.view.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)
}