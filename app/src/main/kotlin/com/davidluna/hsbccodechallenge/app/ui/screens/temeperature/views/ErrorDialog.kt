package com.davidluna.hsbccodechallenge.app.ui.screens.temeperature.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.davidluna.hsbccodechallenge.app.ui.screens.temeperature.Event
import com.davidluna.hsbccodechallenge.domain.AppError
import com.davidluna.hsbccodechallenge.domain.tags.TestTag.ErrorDialog.BUTTON
import com.davidluna.hsbccodechallenge.domain.tags.TestTag.ErrorDialog.CARD

@Composable
fun ErrorDialog(
    appError: AppError?, onEvent: (Event) -> Unit
) {
    appError?.let {
        Dialog(onDismissRequest = { onEvent(Event.ResetError) }) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.testTag(CARD)
            ) {
                Box(
                    modifier = Modifier.padding(48.dp)
                ) {
                    Text(text = it.description)
                }
                Button(
                    onClick = {
                        onEvent(Event.ResetData)
                        onEvent(Event.ResetError)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 16.dp)
                        .testTag(BUTTON),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(text = "Reset Data")
                }
            }
        }
    }
}