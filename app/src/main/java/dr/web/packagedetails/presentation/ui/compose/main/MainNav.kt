package dr.web.packagedetails.presentation.ui.compose.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dr.web.packagedetails.presentation.ui.compose.custom_components.ChangeBarColors
import dr.web.packagedetails.presentation.ui.compose.main.app_list.AppListNav
import dr.web.packagedetails.presentation.ui.navigation.MainNavigation

@Composable
fun MainNav() {
    val navigator = rememberNavController()
    ChangeBarColors(
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF),
        Color(0xFFFFFFFF)
    )
    NavHost(
        startDestination = MainNavigation.AppList,
        navController = navigator,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable<MainNavigation.AppList> {
            AppListNav()
        }
    }
}