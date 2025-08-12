package dr.web.packagedetails.presentation.ui.compose.main.app_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dr.web.packagedetails.presentation.ui.compose.main.app_detail.view_model.AppDetailEvent
import dr.web.packagedetails.presentation.ui.compose.main.app_detail.view_model.AppDetailViewModel
import dr.web.packagedetails.presentation.ui.navigation.AppDetailNavigation

@Composable
fun AppDetailNav(
    packageName: String,
    popUp: () -> Unit
) {
    val navigator = rememberNavController()
    NavHost(
        startDestination = AppDetailNavigation.AppDetail,
        navController = navigator,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable<AppDetailNavigation.AppDetail> {
            val viewModel: AppDetailViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.onTriggerEvent(AppDetailEvent.GetDetailAppInfo(packageName))
            }
            AppDetailScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                errors = viewModel.errors,
                popUp = popUp
            )
        }
    }
}