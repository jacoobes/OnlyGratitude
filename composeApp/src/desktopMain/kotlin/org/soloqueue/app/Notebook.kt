package org.soloqueue.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.dp

@Composable
fun NotebookPaper(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(

        ),
        elevation = CardDefaults.cardElevation(

        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    color = Color(0xFFf9f1e8),
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            content()
        }
    }
}
