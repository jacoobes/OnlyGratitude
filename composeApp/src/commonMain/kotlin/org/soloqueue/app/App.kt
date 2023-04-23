package org.soloqueue.app

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
internal fun App(
    isDarkMode : MutableState<Boolean>,
    content: @Composable () -> Unit
) = AppTheme(isDarkMode.value) {
    content()
}

internal expect fun openUrl(url: String?)