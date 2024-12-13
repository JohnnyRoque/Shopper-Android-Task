package com.iceman.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iceman.data.model.RideOption
import com.iceman.designsystem.components.ShopperButton

@Composable
fun DriverOption(modifier: Modifier = Modifier, rideOption: RideOption,onClick :(RideOption) -> Unit){

    Column(modifier) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Column{
                Text(rideOption.name, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold )
                Text(rideOption.vehicle, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Normal )
                Text("Nota ${rideOption.review.rating.toString()}",style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Normal )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    formatCurrency(rideOption.value),
                    Modifier
                        .padding(end = 4.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.End
                )
                ShopperButton(text = stringResource(R.string.confirm_text), enabled = true) { onClick(rideOption) }
            }
        }
        HorizontalDivider(modifier = Modifier.height(4.dp))
    }

}

