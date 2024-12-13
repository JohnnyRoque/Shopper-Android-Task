package com.iceman.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ShopperBanner(
    modifier: Modifier = Modifier,
    image: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(image),
            contentDescription = contentDescription,
            modifier = modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                null,
                onClick = onClick
            )
        )
    }
}