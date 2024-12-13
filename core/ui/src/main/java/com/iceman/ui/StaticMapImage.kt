package com.iceman.ui

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun StaticMapImage(mapUrl: Uri, modifier: Modifier = Modifier) {
    AsyncImage(

        mapUrl,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier

    )
}

