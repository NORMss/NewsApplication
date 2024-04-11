package com.norm.mynewsapplication.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.norm.mynewsapplication.R
import com.norm.mynewsapplication.domain.model.Article
import com.norm.mynewsapplication.domain.model.Source
import com.norm.mynewsapplication.presentation.Dimens.ArticleCardSize
import com.norm.mynewsapplication.presentation.Dimens.ExtraSmallPadding1
import com.norm.mynewsapplication.presentation.Dimens.ExtraSmallPadding2
import com.norm.mynewsapplication.presentation.Dimens.SmallIcon
import com.norm.mynewsapplication.ui.theme.MyNewsApplicationTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit,
) {
    val context = LocalContext.current

    Row(
        modifier = modifier.clickable {
            onClick()
        }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context = context)
                .data(article.urlToImage)
                .build(),
            contentDescription = null,
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding1)
                .height(ArticleCardSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title,
                ),
                maxLines = 2,
                overflow = TextOverflow.Clip,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body,
                    ),
                )

                Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(SmallIcon),
                    tint = colorResource(id = R.color.body),
                )
                Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body,
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    MyNewsApplicationTheme {
        ArticleCard(
            article = Article(
                author = "Maxim Khoroshev",
                content = "How to connect a VPN",
                description = "Download VPN from NORM and configure via telegram bot",
                publishedAt = "3 hours ago",
                source = Source(id = "rozetked", name = "ROZETKED"),
                title = "The easiest way to install a VPN!",
                url = "normno.t.me",
                urlToImage = "https://rozetked.me/images/uploads/webp/ZtwjyBZC0rUE.webp?1625568647",
            ),
            onClick = {

            }
        )
    }
}