package com.iceman.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iceman.designsystem.components.ShopperCard
import com.iceman.designsystem.theme.ShopperDriveTheme
import com.iceman.data.model.Driver
import com.iceman.data.model.Ride
import java.time.LocalDateTime

@Composable
fun SummaryCard(ride: Ride, modifier: Modifier = Modifier, onClick: () -> Unit) {
    ShopperCard(modifier.wrapContentSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SummaryInfoText(ride = ride)
            Spacer(Modifier.size(16.dp))
            SummaryInfoText(ride = ride)
            Spacer(Modifier.size(16.dp))
            SummaryInfoText(ride = ride)
            TextButton(onClick) {
                Text(
                    "Mostrar todos",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

    }
}

@Preview
@Composable

fun SummaryCardPreview() {
    ShopperDriveTheme {
        SummaryCard(
            Ride(
                "",
                LocalDateTime.now(),
                "Av. Brasil, 2033 - Jardim America, São Paulo - SP, 01431-001",
                "Av. Paulista, 1538 - Bela Vista, São Paulo - SP, 01310-200",
                12,
                "",
                Driver(0, ""),
                19.00
            )
        ) {}
    }
}