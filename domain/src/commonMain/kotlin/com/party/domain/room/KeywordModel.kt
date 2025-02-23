package com.party.domain.room

data class KeywordModel(
    val keyword: String,
)

fun String.toKeywordModel(keyword: String): KeywordModel {
    return KeywordModel(
        keyword = keyword,
    )
}