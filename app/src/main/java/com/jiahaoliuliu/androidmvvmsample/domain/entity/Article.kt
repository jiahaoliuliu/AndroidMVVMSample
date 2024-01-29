package com.jiahaoliuliu.androidmvvmsample.domain.entity

class Article (
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val source: String
) :Comparable<Article>  {
    override fun compareTo(other: Article): Int {
        return this.title compareTo other.title
    }
}