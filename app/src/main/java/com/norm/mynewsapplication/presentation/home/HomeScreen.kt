@file:OptIn(ExperimentalFoundationApi::class)

package com.norm.mynewsapplication.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.norm.mynewsapplication.R
import com.norm.mynewsapplication.domain.model.Article
import com.norm.mynewsapplication.presentation.Dimens.MediumPadding1
import com.norm.mynewsapplication.presentation.common.ArticlesList
import com.norm.mynewsapplication.presentation.common.SearchBar

@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " * ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.news),
//            contentDescription = null,
//            modifier = Modifier
//                .width(128.dp)
//                .height(32.dp)
//                .padding(horizontal = MediumPadding1)
//        )
//        Spacer(
//            modifier = Modifier.height(MediumPadding1)
//        )
        SearchBar(
            text = "",
            readOnly = true,
            onValueChange = { },
            onClick = {
                navigateToSearch()
            },
            onSearch = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding1)
        )
        Spacer(
            modifier = Modifier.height(MediumPadding1)
        )
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.text_title)
        )
        Spacer(
            modifier = Modifier.height(MediumPadding1)
        )

        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = {
                navigateToDetails(it)
            }
        )
    }
}