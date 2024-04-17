package com.norm.mynewsapplication.presentation.news_navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.norm.mynewsapplication.R
import com.norm.mynewsapplication.presentation.Dimens.ExtraSmallPadding1
import com.norm.mynewsapplication.presentation.Dimens.IconSize
import com.norm.mynewsapplication.ui.theme.MyNewsApplicationTheme

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit,
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.background,
        tonalElevation = 16.dp,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = {
                    onItemClick(index)
                },
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(IconSize)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(ExtraSmallPadding1)
                        )
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background,
                )
            )
        }
    }
}

data class BottomNavigationItem(
    val icon: ImageVector,
    val text: String,
)

@Preview
@Preview(
    locale = "ru-rus"
)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewNewsBottomNavigation() {
    MyNewsApplicationTheme {
        NewsBottomNavigation(
            items = listOf(
                BottomNavigationItem(
                    icon = Icons.Default.Home,
                    text = stringResource(R.string.home),
                ),
                BottomNavigationItem(
                    icon = Icons.Default.Search,
                    text = stringResource(R.string.search),
                ),
                BottomNavigationItem(
                    icon = Icons.Default.Star,
                    text = stringResource(R.string.bookmark),
                )
            ),
            selected = 0,
            onItemClick = {

            },
        )
    }
}