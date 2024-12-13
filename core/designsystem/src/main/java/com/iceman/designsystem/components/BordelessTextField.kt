package com.iceman.designsystem.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue


@Composable
fun BordelessTextField(modifier: Modifier = Modifier, userText: TextFieldValue, @StringRes placeholder: Int, type: String, onValueChange: (type: String, textValue: TextFieldValue) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth()
            .testTag("USER_TEXT_INPUT"),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        value = userText,
        onValueChange = { onValueChange(type,it) },
        placeholder = {
            Text(
                text = stringResource(placeholder),
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences
        )
    )
}