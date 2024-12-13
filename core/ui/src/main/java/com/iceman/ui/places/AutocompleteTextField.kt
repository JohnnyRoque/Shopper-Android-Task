package com.iceman.ui.places

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun AutocompleteTextField(viewModel: AutocompleteViewModel = koinViewModel()) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.getAutocompletePredictions(it.text)
            },
            modifier = Modifier.fillMaxWidth()
        )

        val predictions by viewModel.predictions.collectAsState()

        predictions.take(1).forEach { prediction ->
            Text(text = prediction.getFullText(null).toString())
        }
    }
}
