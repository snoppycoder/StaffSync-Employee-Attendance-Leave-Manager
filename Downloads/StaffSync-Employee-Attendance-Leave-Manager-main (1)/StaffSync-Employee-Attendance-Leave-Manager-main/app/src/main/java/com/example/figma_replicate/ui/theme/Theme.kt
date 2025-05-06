package com.example.figma_replicate.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = coral,              // Bright coral used for "Check In" button, active date, and icons
    secondary = Purple40,         // Slightly bold purple for secondary elements or highlights
    tertiary = PurpleGrey40,      // Dimmed purple-gray for low emphasis text or UI
    background = customGray,     // Screen background is clearly white
    surface = PurpleGrey80,       // Light gray for cards like "Check In", "Break Time"
    onPrimary = Color.White,      // Text on coral buttons
    onSecondary = Color.White,    // Optional; not heavily used here
    onBackground = Color.Black,   // Main content text ("Today Attendance", times)
    onSurface = PurpleGrey40      // Labels inside cards ("Break Time", "Go Home")
)



//    Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),



@Composable
fun Figma_replicateTheme(
    darkTheme: Boolean = false,
    // just for now
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}