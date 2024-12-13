package com.iceman.designsystem.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iceman.designsystem.theme.ShopperDriveTheme

@Composable
fun ShopperTextField(
    modifier: Modifier = Modifier,
    userText: TextFieldValue,
    placeholderText: String,
    keyboardActions: KeyboardActions,
    keyboardOption: KeyboardOptions,
    onTextChange: (TextFieldValue) -> Unit
) {
    TextField(
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOption,
        modifier = modifier
            .clip(CircleShape)
            .fillMaxWidth(),
        onValueChange = { onTextChange(it) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        ),
        value = userText,
        singleLine = true,
        placeholder = { Text(text = placeholderText) },
    )

}

