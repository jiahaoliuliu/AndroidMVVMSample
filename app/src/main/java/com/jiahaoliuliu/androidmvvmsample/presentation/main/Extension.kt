package com.jiahaoliuliu.androidmvvmsample.presentation.main

import com.jiahaoliuliu.androidmvvmsample.domain.entity.Article

fun List<Article>.sortByTitle(): List<Article> {
    val result = mutableListOf<Article>()
    this.forEach {
        result.add(it)
    }
    for (i in 0 until result.size - 1) { // [0, 2, 1, 5] i from 0..2
        for (j in result.size -1 downTo  i + 1) { // j from 3 to 1
            if (result[j].title < result[j-1].title) {
                result.swap(j, j -1)
            }
        }
    }
    return result
}

fun <T> MutableList<T>.swap(i: Int, j:Int) {
    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}