package com.example.modcustoms.ui.theme

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

// COLORES FIJOS PERSONALIZADOS (para que NO dependa del wallpaper del celular)
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00695C),       // Verde petróleo elegante (o cambia por el color que quieras)
    secondary = Color(0xFF03DAC6),     // Turquesa bonito como acento
    tertiary = Color(0xFF3700B3),

    background = Color.White,          // ← FONDO BLANCO FIJO
    surface = Color.White,             // ← SUPERFICIES BLANCAS
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF018786),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF121212),    // Fondo oscuro real
    surface = Color(0xFF1E1E1E),
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun ModCustomsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // DESACTIVAMOS LOS COLORES DINÁMICOS para que no tome el color del wallpaper
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