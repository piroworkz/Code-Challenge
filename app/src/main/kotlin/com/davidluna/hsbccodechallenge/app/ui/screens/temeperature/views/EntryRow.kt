package com.davidluna.hsbccodechallenge.app.ui.screens.temeperature.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.davidluna.hsbccodechallenge.domain.tags.TestTag.Temperature.ENTRY_ROW
import com.davidluna.hsbccodechallenge.domain.tags.TestTag.Temperature.VALUE_TEXT_FIELD

@Composable
fun EntryRow(
    title: Int,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .testTag(ENTRY_ROW),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(title),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            style = MaterialTheme.typography.labelLarge.copy(
                textAlign = TextAlign.End
            )
        )
        Spacer(modifier = Modifier.width(24.dp))
        Box(
            modifier = Modifier
                .weight(2f)
                .border(1.dp, MaterialTheme.colorScheme.onSurface)
        ) {
            Text(
                text = value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .testTag(VALUE_TEXT_FIELD),
            )
        }
    }
}
