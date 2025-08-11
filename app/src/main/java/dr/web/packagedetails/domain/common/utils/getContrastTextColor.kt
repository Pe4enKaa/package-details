package dr.web.packagedetails.domain.common.utils

import androidx.compose.ui.graphics.Color

fun getContrastTextColor(backgroundColor: Color): Color {
    val red = backgroundColor.red * 255
    val green = backgroundColor.green * 255
    val blue = backgroundColor.blue * 255

    val luminance = (0.299 * red + 0.587 * green + 0.114 * blue) / 255

    return if (luminance > 0.5) Color.Black else Color.White
}