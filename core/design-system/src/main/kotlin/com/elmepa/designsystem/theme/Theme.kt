package com.elmepa.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = White,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = DarkModeSurface, //App background color,
    onBackground = Color.White, //Text/icons on background
    surface = DarkModeElement2, //UI surfaces like cards, sheets, dialogs
    onSurface = Color.White, //Text/icons on surfaces
    error = Color.Red, //Error messages, warning indicators
    onError = Color.White, //wText/icons on error color
)

private val LightColorScheme = lightColorScheme(
    primary = Black,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = GreyBg, //App background color,
    onBackground = Color.Black, //Text/icons on background
    surface = Color.White, //UI surfaces like cards, sheets, dialogs
    onSurface = Color.Black, //Text/icons on surfaces
    error = Color.Red, //Error messages, warning indicators
    onError = Color.White, //wText/icons on error color
)

@Composable
fun ElmepaAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    /**
     * In order to add custom properties to the MaterialTheme, use CompositionLocalProvider.
     */
    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

val LocalSpacing = staticCompositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    get() = LocalSpacing.current
