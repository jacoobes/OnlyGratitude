package org.soloqueue.app

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import compose.icons.FeatherIcons
import compose.icons.feathericons.Moon
import compose.icons.feathericons.SkipBack
import compose.icons.feathericons.Sun


@Composable
fun OpenMentalHealthResources() {
    IconButton(
        onClick = {
            //todo: redirect to mental health
        }
    ) {

        Icon(
            Icons.Sharp.ExitToApp, contentDescription = "Open resources"
        )
    }
}

@Composable
fun DarkModeIcon() {
    Icon(
        FeatherIcons.Moon,
        null
    )
}
@Composable
fun LightModeIcon() {
    Icon(
        FeatherIcons.Sun,
        null
    )
}
@Composable
fun LightMode(isDarkModeState: MutableState<Boolean>) {
    IconButton(
        onClick = {
            isDarkModeState.value = !isDarkModeState.value
        }
    ) {
        if(isDarkModeState.value) {
            DarkModeIcon()
        } else {
            LightModeIcon()
        }
    }
}

@Composable
fun GoBack(emptyState: MutableState<Boolean>) {
    IconButton({
        emptyState.value = true
    }) {
        Icon(FeatherIcons.SkipBack, "")
    }
}
