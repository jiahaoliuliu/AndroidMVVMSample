package com.jiahaoliuliu.androidmvvmsample.domain.usecase

import android.util.Log
import com.jiahaoliuliu.androidmvvmsample.data.mapper.TopHeadlineMapper
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.domain.repository.TopHeadlineRepository
import javax.inject.Inject

interface RetrieveTopHeadlineUseCase {
    suspend operator fun invoke(country: String): Result<List<Article>>
}

class RetrieveTopHeadlineUseCaseImpl @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository,
    private val topHeadlineMapper: TopHeadlineMapper
    ): RetrieveTopHeadlineUseCase {

    override suspend fun invoke(country: String): Result<List<Article>> {
        return runCatching {
            topHeadlineRepository.getTopHeadlines(country)
                .map { articleRemoteData ->
                        topHeadlineMapper.invoke(articleRemoteData)
                }
        }.onFailure {
            Log.v("Failure", "Error requesting the list of articles ${it.printStackTrace()}")
        }
    }
}
