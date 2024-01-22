package com.davidluna.hsbccodechallenge.domain.tags

sealed interface TestTag {

    data object Temperature : TestTag {
        private const val TEMPERATURE_SCREEN = "TEMPERATURE_SCREEN"
        const val COLUMN = "${TEMPERATURE_SCREEN}_COLUMN"
        const val ENTRY_ROW = "${TEMPERATURE_SCREEN}_ENTRY_ROW"
        const val VALUE_TEXT_FIELD = "${TEMPERATURE_SCREEN}_VALUE_TEXT_FIELD"
        const val NEXT_BUTTON = "${TEMPERATURE_SCREEN}_NEXT_BUTTON"
    }

    data object ErrorDialog : TestTag {
        private const val ERROR_DIALOG = "ERROR_DIALOG"
        const val CARD = "${ERROR_DIALOG}_CARD"
        const val BUTTON = "${ERROR_DIALOG}_BUTTON"
    }
}