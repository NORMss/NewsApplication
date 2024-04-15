package com.norm.mynewsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.norm.mynewsapplication.data.local.NewsDao
import com.norm.mynewsapplication.domain.model.Article
import com.norm.mynewsapplication.domain.model.Source
import com.norm.mynewsapplication.presentation.nvgraph.NavGraph
import com.norm.mynewsapplication.ui.theme.MyNewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var dao: NewsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition(
                condition = { viewModel.splashCondition }
            )
        }
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            dao.upsert(
                Article(
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
                )
            )
        }

        setContent {
            MyNewsApplicationTheme {
                Scaffold { padding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = padding.calculateTopPadding(),
                                bottom = padding.calculateBottomPadding()
                            )
                    ) {
                        val startDestination = viewModel.startDestination
                        NavGraph(
                            startDestination = startDestination,
                        )
                    }
                }
            }
        }
    }
}