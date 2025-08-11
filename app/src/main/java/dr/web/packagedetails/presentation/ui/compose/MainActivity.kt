package dr.web.packagedetails.presentation.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dr.web.packagedetails.presentation.ui.compose.main.MainNav
import dr.web.packagedetails.presentation.ui.navigation.AppNavigation
import dr.web.packagedetails.presentation.ui.theme.PackageDetailsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PackageDetailsTheme {
                val navigator = rememberNavController()
                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navigator,
                        startDestination = AppNavigation.Main,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        composable<AppNavigation.Main> {
                            MainNav()
                        }
                    }
                }
            }
        }
    }
}