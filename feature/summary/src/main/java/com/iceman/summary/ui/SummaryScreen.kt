package com.iceman.summary.ui
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iceman.data.model.Ride

@Composable
fun TripHistoryScreen(
    tripList: List<Ride>,
    onFilterApplied: (String, String) -> Unit // Callback to handle filter logic
) {
    var userId by remember { mutableStateOf("") }
    var selectedDriver by remember { mutableStateOf("Todos") }

    val driverOptions = listOf("Todos", "Motorista 1", "Motorista 2", "Motorista 3")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // User ID Input
        Text(text = "ID do Usuário", fontSize = 16.sp)
        BasicTextField(
            value = userId,
            onValueChange = { userId = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
                .padding(8.dp)
        )

        // Driver Selector Dropdown
        Text(text = "Motorista", fontSize = 16.sp)
        var expanded by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selectedDriver)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                driverOptions.forEach { driver ->
                    DropdownMenuItem(
                        text = { Text(driver) },
                        onClick = {
                            selectedDriver = driver
                            expanded = false
                        }
                    )
                }
            }
        }

        // Apply Filter Button
        Button(
            onClick = { onFilterApplied(userId, selectedDriver) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Aplicar Filtro")
        }

        // Trip List Header
        Text(
            text = "Viagens Realizadas",
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Trip List
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tripList.size) { index ->
                val trip = tripList[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Data e Hora: ${trip.date}", fontSize = 14.sp)
                        Text("Motorista: ${trip.driver.name}", fontSize = 14.sp)
                        Text("Origem: ${trip.origin}", fontSize = 14.sp)
                        Text("Destino: ${trip.destination}", fontSize = 14.sp)
                        Text("Distância: ${trip.distance}", fontSize = 14.sp)
                        Text("Tempo: ${trip.duration}", fontSize = 14.sp)
                        Text("Valor: ${trip.value}", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}