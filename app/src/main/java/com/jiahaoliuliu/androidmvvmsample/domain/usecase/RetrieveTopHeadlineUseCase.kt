package com.jiahaoliuliu.androidmvvmsample.domain.usecase

import com.jiahaoliuliu.androidmvvmsample.data.mapper.TopHeadlineMapper
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article
import com.jiahaoliuliu.androidmvvmsample.domain.repository.TopHeadlineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface RetrieveTopHeadlineUseCase {
    suspend operator fun invoke(country: String): Flow<List<Article>>
}

class RetrieveTopHeadlineUseCaseImpl @Inject constructor(
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
