package com.andreeau.polls.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.andreeau.pollscomponents.ui.DarkPurple

private val DarkColorScheme = darkColorScheme(
    primary = Purple,
    secondary = DeepPurple,
    tertiary = DarkYellow,
    background = DarkerPurple,
    onBackground = DeepDarkPurple,
    surface = DarkPurple
)
private val LightColorScheme = lightColorScheme(
    primary = Purple,
    secondary = DeepPurple,
    tertiary = Yellow,
    background = LightPurple,
    onBackground = DarkPurple,
    surface = White
)

@Composable
fun PollsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}