package com.iceman.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.iceman.designsystem.components.ShopperCard
import com.iceman.domain.model.ScreenState
import com.iceman.ui.CalculateTripCard
import com.iceman.ui.OpenPlayStoreButton
import com.iceman.ui.SummaryInfoText
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onClick: (ScreenState) -> Unit

) {
    val screenState by viewModel.screenState.collectAsState()
    val rideOverviewState by viewModel.rideOverviewState.collectAsState()
    val estimateTripState by viewModel.estimateTripState.collectAsState()


    var isFieldsNotEmpty = remember {
        derivedStateOf {
            viewModel.originTextFieldValue.text.isNotEmpty() &&
                    viewModel.destinationTextFieldValue.text.isNotEmpty() &&
                    viewModel.iDTextFieldValue.text.isNotEmpty()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        CalculateTripCard(
            modifier = Modifier.padding(bottom = 4.dp),
            originTextFieldValue = viewModel.originTextFieldValue,
            destinationTextFieldValue = viewModel.destinationTextFieldValue,
            iDTextFieldValue = viewModel.iDTextFieldValue,
            enabled = isFieldsNotEmpty.value,
            onSuggestionCLick = {type,suggestion ->
                viewModel.updateUserText(type, TextFieldValue(suggestion))
            },
            onTextChange = { type, newValue ->
                viewModel.updateUserText(type, newValue)
            }
        ) {
            viewModel.estimateTripValue()

                val state = ScreenState(
            userId = viewModel.iDTextFieldValue.text,
            origin = viewModel.originTextFieldValue.text,
            destination = viewModel.destinationTextFieldValue.text,
            estimateTrip = estimateTripState
            )

            viewModel.updateScreenState(state)


            Log.d("STATE","$state")


            onClick(
                screenState
            )
        }
        Spacer(Modifier.size(12.dp))

        Text(
            stringResource(R.string.summary_text),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        ShopperCard {
            if (rideOverviewState.rides.isNotEmpty()) {
                LazyColumn {
                    items(rideOverviewState.rides.take(3)) {
                        SummaryInfoText(ride = it)
                    }
                }
            } else {
                Text("O histórico esta vázio", Modifier.padding(24.dp))
            }
        }
        Spacer(Modifier.size(12.dp))

        Text(
            stringResource(R.string.suggestion_text),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        OpenPlayStoreButton(image = R.drawable.banner)
    }

}
