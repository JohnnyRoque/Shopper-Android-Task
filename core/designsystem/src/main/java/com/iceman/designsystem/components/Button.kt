package com.iceman.designsystem.components

import android.R.attr.onClick
import android.R.attr.text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iceman.designsystem.theme.ShopperDriveTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ShopperButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled : Boolean,
    onClick: () -> Unit

) {
    var clickCount by remember { mutableIntStateOf(0) }
    val onButtonClick = debounce<Unit> { clickCount++ }
    Button(
        { onButtonClick(onClick()) },
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
    {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}


@Composable
fun <T> debounce(delayMillis: Long = 300L, onDebounced: (T) -> Unit): (T) -> Unit {

    var debounceJob by remember { mutableStateOf<Job?>(null) }
    return {param:T ->
        debounceJob?.cancel()
        debounceJob = CoroutineScope(Dispatchers.Default).launch{
            delay(delayMillis)
            onDebounced
        }
    }
//    var debounceJob by remember { mutableStateOf<Job?>(null) } return { param: T ->
//        debounceJob?.cancel() debounceJob = CoroutineScope (Dispatchers.Main).launch {
//            delay(
//                delayMillis
//            ) onDebounced (param)
//        }

}

@Preview
@Composable
fun ShopperButtonPreview() {
    ShopperDriveTheme {
        ShopperButton("Calcular corrida", Modifier.padding(16.dp),true) {}

    }
}