package com.jiahaoliuliu.androidmvvmsample.data.repository

import com.jiahaoliuliu.androidmvvmsample.data.api.NetworkService
import com.jiahaoliuliu.androidmvvmsample.data.model.ArticleRemoteData
import com.jiahaoliuliu.androidmvvmsample.data.model.SourceRemoteData
import com.jiahaoliuliu.androidmvvmsample.data.model.TopHeadlinesResponse
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TopHeadlineRepositoryImplTest {

    @MockK
    lateinit var networkService: NetworkService

    private lateinit var repo: TopHeadlineRepositoryImpl

    @Before
    fun setUp() {
        repo = TopHeadlineRepositoryImpl(networkService)
    }

    @Test
    fun test1() {
        runBlocking {
            // Given
            val source = SourceRemoteData(
                id = "My.company",
                name = "My company"
            )
            val articleRemote = ArticleRemoteData(
                title = "My title",
                description = "Someone was killed",
                url = "http://google.com",
                urlToImage = "http://google.com/images",
                source = source
            )
            val articlesList = listOf(articleRemote)

            val response = TopHeadlinesResponse (
                status = "200",
                totalResults = 1,
                articles = listOf(articleRemote)
            )
            coEvery { networkService.getTopHeadlines(any()) } returns response

            // When
            val result = repo.getTopHeadlines("US")

            // Then
            assertEquals(articlesList, result)
        }
    }
}