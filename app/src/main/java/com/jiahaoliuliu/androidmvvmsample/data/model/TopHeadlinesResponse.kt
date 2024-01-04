package com.jiahaoliuliu.androidmvvmsample.data.model

data class TopHeadlinesResponse (
    val status: String = "",
    val totalResults: Int = 0,
    val articles: List<ArticleRemoteData> = ArrayList()
)