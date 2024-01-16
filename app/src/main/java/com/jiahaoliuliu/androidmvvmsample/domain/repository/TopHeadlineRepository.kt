package com.jiahaoliuliu.androidmvvmsample.domain.repository

import com.jiahaoliuliu.androidmvvmsample.data.model.ArticleRemoteData

interface TopHeadlineRepository {
    suspend fun getTopHeadlines(country: String): List<ArticleRemoteData>
}