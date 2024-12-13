package com.iceman.ui

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iceman.designsystem.theme.ShopperDriveTheme
import com.iceman.data.model.Driver
import com.iceman.data.model.Ride
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun SummaryInfoText(modifier: Modifier = Modifier, ride: Ride) {
    Column(modifier) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Column{
                Text(ride.destination.split("-").take(2).joinToString("-"), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium )
                Text(formatDateTime(ride.date), style = MaterialTheme.typography.bodyMedium )
            }
            Text(formatCurrency(ride.value), Modifier.padding(end = 4.dp).fillMaxWidth(), textAlign = TextAlign.End)
        }
        HorizontalDivider(modifier = Modifier.height(4.dp))
    }
}

@Preview
@Composable
fun SummaryInfoTextPreview() {
    ShopperDriveTheme {
        SummaryInfoText(
            ride = Ride(
                "",
                LocalDateTime.now(),
                "Av. Brasil, 2033 - Jardim America, São Paulo - SP, 01431-001",
                "Av. Paulista, 1538 - Bela Vista, São Paulo - SP, 01310-200",
                12,
                "",
                Driver(0, ""),
                19.00
            )
        )
    }
}

fun formatDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM ' - ' HH:mm", Locale("pt", "BR"))
    return dateTime.format(formatter)
}


fun formatCurrency(amount: Double, locale: Locale = Locale.getDefault()): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
    return currencyFormatter.format(amount)
}