package com.iceman.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.iceman.designsystem.components.ShopperButton
import com.iceman.designsystem.components.ShopperCard
import com.iceman.designsystem.components.ShopperTextField
import com.iceman.ui.places.AutocompleteViewModel
import org.koin.androidx.compose.koinViewModel

const val DESTINATION = "Destination"
const val ORIGIN = "Origin"
const val ID = "Id"


@Composable
fun CalculateTripCard(
    modifier: Modifier = Modifier,
    originTextFieldValue: TextFieldValue,
    destinationTextFieldValue: TextFieldValue,
    iDTextFieldValue: TextFieldValue,
    autoCompleteViewModel: AutocompleteViewModel = koinViewModel(),
    enabled: Boolean,
    onTextChange: (String, TextFieldValue) -> Unit,
    onSuggestionCLick: (String, String) -> Unit,
    onClick: () -> Unit,


    ) {
    var focusedField: String by remember { mutableStateOf("") }

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }

    val focusManager = LocalFocusManager.current
    ShopperCard(modifier) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            val predictions by autoCompleteViewModel.predictions.collectAsState()

            ShopperTextField(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .focusRequester(focusRequester1)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            autoCompleteViewModel.clearPredictions()
                        }
                    },
                userText = originTextFieldValue,
                placeholderText = stringResource(R.string.starting_point),
                keyboardActions =
                KeyboardActions(onNext = { focusRequester2.requestFocus() }),
                keyboardOption = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)

            ) {
                autoCompleteViewModel.getAutocompletePredictions(it.text)
                onTextChange(ORIGIN, it)
                focusedField = ORIGIN

                if (originTextFieldValue.text.isEmpty()) {
                    autoCompleteViewModel.clearPredictions()
                }
            }

            ShopperTextField(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .focusRequester(focusRequester2)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            autoCompleteViewModel.clearPredictions()
                        }
                    },

                userText = destinationTextFieldValue,
                placeholderText = stringResource(R.string.where_to_text),
                keyboardActions = KeyboardActions(onNext = { focusRequester3.requestFocus() }),
                keyboardOption = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            ) {
                autoCompleteViewModel.getAutocompletePredictions(it.text)
                onTextChange(DESTINATION, it)
                focusedField = DESTINATION

                if (destinationTextFieldValue.text.isEmpty()) {
                    autoCompleteViewModel.clearPredictions()
                }
            }

            ShopperTextField(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .focusRequester(focusRequester3)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            autoCompleteViewModel.clearPredictions()
                        }
                    },
                userText = iDTextFieldValue,
                placeholderText = stringResource(R.string.tag_text_placeholder),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOption = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)

            ) {
                onTextChange(ID, it)
            }

            LazyColumn(Modifier.padding(horizontal = 12.dp)) {
                items(predictions.take(1)) { prediction ->
                    Text(
                        text = prediction
                            .getFullText(null)
                            .toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSuggestionCLick(focusedField,prediction.getFullText(null).toString())
                                autoCompleteViewModel.clearPredictions()
                                focusManager.clearFocus()
                             }
                    )
                }
            }


            ShopperButton(
                modifier = Modifier.padding(vertical = 8.dp),
                text = stringResource(R.string.calculate_race),
                onClick = onClick,
                enabled = enabled
            )
        }
    }
}
