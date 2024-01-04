package com.jiahaoliuliu.androidmvvmsample.data.mapper

import com.jiahaoliuliu.androidmvvmsample.data.model.ArticleRemoteData
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article

class TopHeadlineMapper {
    fun invoke(articleRemoteData: ArticleRemoteData): Article {
        return Article(
            title = articleRemoteData.title,
            description = articleRemoteData.description ?: "",
            url = articleRemoteData.url,
            imageUrl = articleRemoteData.urlToImage,
            source = articleRemoteData.source?.name ?: ""
        )
    }
}