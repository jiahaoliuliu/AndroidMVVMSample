package com.jiahaoliuliu.androidmvvmsample.domain.repository

import com.jiahaoliuliu.androidmvvmsample.data.model.ArticleRemoteData
import kotlinx.coroutines.flow.Flow

interface TopHeadlineRepository {
    suspend fun getTopHeadlines(country: String): Flow<List<ArticleRemoteData>>
}