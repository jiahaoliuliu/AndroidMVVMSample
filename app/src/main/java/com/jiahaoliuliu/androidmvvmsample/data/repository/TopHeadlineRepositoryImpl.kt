package com.jiahaoliuliu.androidmvvmsample.data.repository

import com.jiahaoliuliu.androidmvvmsample.data.api.NetworkService
import com.jiahaoliuliu.androidmvvmsample.data.model.ArticleRemoteData
import com.jiahaoliuliu.androidmvvmsample.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopHeadlineRepositoryImpl @Inject constructor(private val networkService: NetworkService): TopHeadlineRepository {
    override fun getTopHeadlines(country: String): Flow<List<ArticleRemoteData>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }
}