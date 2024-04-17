package com.norm.mynewsapplication.presentation.details

import com.norm.mynewsapplication.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    data object RemoveSideEffect : DetailsEvent()
}