package dr.web.packagedetails.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColorPalette =
    lightColorScheme(
        primary = PrimaryColor,
        primaryContainer = PrimaryVariantColor,
        secondary = AccentColor,
        background = Color.White,
        surfaceVariant = Color.White,
        surface = lightSurface,
        onPrimary = OnPrimaryLight,
        onSecondary = OnSecondaryLight,
        onBackground = OnBackgroundLight,
        onSurface = OnSurfaceLight,
    )

@Composable
fun PackageDetailsTheme(
    //darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorPalette,
        typography = latoTypography(),
        content = content,
    )
}