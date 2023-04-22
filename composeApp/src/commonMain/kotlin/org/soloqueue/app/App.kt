package org.soloqueue.app

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun App(content: @Composable () -> Unit) = AppTheme {
    content()
}

internal expect fun openUrl(url: String?)