package com.davidluna.hsbccodechallenge.app.ui.screens.temeperature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidluna.hsbccodechallenge.R
import com.davidluna.hsbccodechallenge.app.ui.screens.temeperature.views.EntryRow
import com.davidluna.hsbccodechallenge.app.ui.screens.temeperature.views.ErrorDialog
import com.davidluna.hsbccodechallenge.app.ui.screens.temeperature.views.LoadingIndicator
import com.davidluna.hsbccodechallenge.app.ui.theme.HSBCCodeChallengeTheme
import com.davidluna.hsbccodechallenge.domain.tags.TestTag.Temperature.COLUMN
import com.davidluna.hsbccodechallenge.domain.tags.TestTag.Temperature.NEXT_BUTTON

@Composable
fun TemperatureScreen(
    state: TemperatureViewModel.State,
    onEvent: (Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag(COLUMN),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EntryRow(
            title = R.string.title_location,
            value = state.randomData?.place ?: ""
        )
        Spacer(modifier = Modifier.height(16.dp))
        EntryRow(
            title = R.string.title_temperature,
            value = buildTempString(state)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { onEvent(Event.RandomTemp) },
            modifier = Modifier
                .testTag(NEXT_BUTTON),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(text = stringResource(R.string.btn_next))
        }
    }
    LoadingIndicator(state.isLoading)

    ErrorDialog(
        appError = state.error,
        onEvent = { onEvent(it) }
    )
}

private fun buildTempString(state: TemperatureViewModel.State): String {
    return if (state.randomData != null) {
        state.randomData.value.toString().plus(" ${state.randomData.unit}")
    } else {
        ""
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun TemperatureScreenPreview() {
    HSBCCodeChallengeTheme {
        TemperatureScreen(
            state = TemperatureViewModel.State(isLoading = true),
            onEvent = {}
        )
    }
}