package org.soloqueue.app

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.soloqueue.app.journal.JournalView

@Composable
internal fun App() = AppTheme {
    JournalView()
}

internal expect fun openUrl(url: String?)