package com.norm.mynewsapplication.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.norm.mynewsapplication.presentation.Dimens.IndicatorSize
import com.norm.mynewsapplication.ui.theme.PurpleGrey80


@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = PurpleGrey80,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        repeat(pageSize) { page ->
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}

@Preview
@Composable
fun PreviewPageIndicator(){
    PageIndicator(
        modifier = Modifier.width(64.dp),
        pageSize = 3,
        selectedPage = 1,
    )
}