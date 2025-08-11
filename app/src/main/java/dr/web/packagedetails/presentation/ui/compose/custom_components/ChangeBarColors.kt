package dr.web.packagedetails.presentation.ui.compose.custom_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun ChangeBarColors(
    statusBarColor: Color?,
    navigationBarColor: Color?,
    defaultStatusBarColor: Color,
    defaultNavigationBarColor: Color
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(statusBarColor?: defaultStatusBarColor)
    systemUiController.setNavigationBarColor(navigationBarColor?: defaultNavigationBarColor)
}
