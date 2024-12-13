package com.iceman.ui

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun OpenPlayStoreButton(modifier: Modifier = Modifier, @DrawableRes image: Int) {
    val context = LocalContext.current
    Image(
        painter = painterResource(image),
        null,
        modifier = modifier
            .clip(
                RoundedCornerShape(16.dp)
            )
            .clickable(true)
            {
                val appPackageName =
                    "br.com.shopper.shopperapp"
                try {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appPackageName")
                        )
                    )
                } catch (_: Exception) {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                        )
                    )
                }
            }
            .height(100.dp),
        contentScale = ContentScale.FillBounds
    )
}