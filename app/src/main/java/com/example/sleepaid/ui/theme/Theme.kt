package com.example.sleepaid.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val SleepAidColors = lightColorScheme(
    primary = PeriwinkleDark,
    onPrimary = Color.White,
    secondary = Periwinkle,
    onSecondary = Navy,
    background = Cream,
    onBackground = Navy,
    surface = CreamLight,
    onSurface = Navy,
    error = ErrorRed,
    onError = Color.White
)

@Composable
fun SleepAidTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SleepAidColors,
        content = content
    )
}