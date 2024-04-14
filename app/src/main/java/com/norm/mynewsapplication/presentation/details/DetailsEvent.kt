package com.norm.mynewsapplication.presentation.details

sealed class DetailsEvent {
    data object SaveArticle : DetailsEvent()
}