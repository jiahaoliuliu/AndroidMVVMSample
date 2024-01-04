package com.jiahaoliuliu.androidmvvmsample.data.model

data class ArticleRemoteData (
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val source: SourceRemoteData?
)