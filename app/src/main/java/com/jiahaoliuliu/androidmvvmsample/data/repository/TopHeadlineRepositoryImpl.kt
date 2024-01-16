package com.jiahaoliuliu.androidmvvmsample.data.repository

import com.jiahaoliuliu.androidmvvmsample.data.api.NetworkService
import com.jiahaoliuliu.androidmvvmsample.data.model.ArticleRemoteData
import com.jiahaoliuliu.androidmvvmsample.domain.repository.TopHeadlineRepository
import javax.inject.Inject

class TopHeadlineRepositoryImpl @Inject constructor(private val networkService: NetworkService): TopHeadlineRepository {
    override suspend fun getTopHeadlines(country: String): List<ArticleRemoteData> {
        return networkService.getTopHeadlines(country).articles
            .filter {
                it.title != "[Removed]"
            }
    }
}