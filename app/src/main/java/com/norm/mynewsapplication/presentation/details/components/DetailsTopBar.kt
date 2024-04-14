@file:OptIn(ExperimentalMaterial3Api::class)

package com.norm.mynewsapplication.presentation.details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.norm.mynewsapplication.R

@Composable
fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = {

        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(
                onClick = onBookmarkClick
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                )
            }
            IconButton(
                onClick = onShareClick
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                )
            }
            IconButton(
                onClick = onBrowsingClick
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                )
            }
        }
    )
}