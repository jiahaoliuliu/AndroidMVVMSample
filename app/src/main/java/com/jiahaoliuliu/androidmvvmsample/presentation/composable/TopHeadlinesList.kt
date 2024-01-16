package com.jiahaoliuliu.androidmvvmsample.presentation.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article

@Composable
fun TopHeadlinesList(articlesList: List<Article>) {
    var isLoading by remember { mutableStateOf(true) }
    isLoading = articlesList.isEmpty()

    if (isLoading) {
        IndeterminateCircularIndicator()
    }

    LazyColumn {
        items(articlesList) {article ->
            TopHeadline(article)
        }
    }
}
