package com.jiahaoliuliu.androidmvvmsample.domain.usecase

import com.jiahaoliuliu.androidmvvmsample.data.mapper.TopHeadlineMapper
import com.jiahaoliuliu.androidmvvmsample.data.repository.TopHeadlineRepository
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface RetrieveTopHeadlineUseCase {
    suspend operator fun invoke(country: String): Flow<List<Article>>
}

class RetrieveTopHeadlineUseCaseImpl (
    private val topHeadlineRepository: TopHeadlineRepository,
    private val topHeadlineMapper: TopHeadlineMapper
    ): RetrieveTopHeadlineUseCase {

    override suspend fun invoke(country: String): Flow<List<Article>> {
        return topHeadlineRepository.getTopHeadlines(country)
            .map { it.map {
                articleRemoteData ->  topHeadlineMapper.invoke(articleRemoteData)
                }
            }
        }
    }
