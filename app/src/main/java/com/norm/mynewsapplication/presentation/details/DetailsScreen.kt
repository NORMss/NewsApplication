package com.norm.mynewsapplication.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import com.norm.mynewsapplication.domain.model.Article
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.norm.mynewsapplication.R
import com.norm.mynewsapplication.domain.model.Source
import com.norm.mynewsapplication.presentation.Dimens.ArticleImageHeight
import com.norm.mynewsapplication.presentation.Dimens.MediumPadding1
import com.norm.mynewsapplication.presentation.details.components.DetailsTopBar
import com.norm.mynewsapplication.ui.theme.MyNewsApplicationTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent.UpsertDeleteArticle) -> Unit,
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteArticle(article))
            },
            onBackClick = navigateUp,
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1,
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest
                        .Builder(context = context)
                        .data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop,
                )

                Spacer(
                    modifier = Modifier
                        .height(MediumPadding1)
                )

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )

                Spacer(
                    modifier = Modifier
                        .height(MediumPadding1)
                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailsScreen() {
    MyNewsApplicationTheme {
        DetailsScreen(
            article = Article(
                author = "Наука и техника",
                title = "Google представила собственный процессор",
                content = "Корпорация Google представила собственный процессор. Об этом сообщается в пресс-релизе на сайте компании.\n" +
                        "\n" +
                        "Процессор получил название Axion. Он основан на ядрах Neoverse V2 и имеет систему специально настроенных кремниевых микроконтроллеров Titanium. Также чип базируется на стандартной архитектуре и наборе команд Armv9.\n" +
                        "\n" +
                        "Новый продукт Google будет предназначен для использования в центрах обработки данных (ЦОД). По словам инженеров корпорации, он обеспечивает лучшую в отрасли производительность и энергоэффективность. Клиенты Google получат доступ к системам на основе нового процессора до конца года. Представители корпорации рассказали, что уже начали развертывать инфраструктуру своих сервисов на ARM-процессорах.\n" +
                        "\n" +
                        "По словам специалистов телеканала CNBC, с выпуском нового процессора Google продолжает развивать систему облачной инфраструктуры. Alphabet, материнская компания Google, получает почти 11 процентов дохода от облачных технологий. Больше всего — около 25 процентов дохода — корпорация получает от рекламы.\n" +
                        "\n" +
                        "В конце февраля Google анонсировала модель искусственного интеллекта (ИИ) Gemma для разработчиков. Модель представляет собой «семейство легких современных открытых моделей» и имеет открытый исходный код.",
                description = "Google представила ARM-процессор Axion для центров обработки данных",
                publishedAt = "16:33, 10-04-2024",
                source = Source(
                    id = "lenta",
                    name = "Lenta",
                ),
                url = "https://lenta.ru/news/2024/04/10/axion/",
                urlToImage = "https://icdn.lenta.ru/images/2024/04/09/17/20240409175952348/owl_detail_620_f14078d2529e637aaf5d8b641514fb24.jpg",
            ),
            event = { },
            navigateUp = { }
        )
    }
}