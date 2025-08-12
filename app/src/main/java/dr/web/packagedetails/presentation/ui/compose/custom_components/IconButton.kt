package dr.web.packagedetails.presentation.ui.compose.custom_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dr.web.packagedetails.domain.common.utils.getContrastTextColor
import dr.web.packagedetails.presentation.ui.theme.CustomCardColorsTheme

@Composable
fun IconButton(
    modifier : Modifier = Modifier,
    imageVector: ImageVector,
    containerColor: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.size(20.dp),
        colors = CustomCardColorsTheme(containerColor, getContrastTextColor(containerColor)),
        elevation = CardDefaults.cardElevation(0.dp),
        onClick = {
            onClick()
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector, null)
        }
    }
}