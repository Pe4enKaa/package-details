package dr.web.packagedetails.presentation.ui.compose.main.app_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dr.web.packagedetails.presentation.ui.compose.main.app_list.view_model.AppListEvent
import dr.web.packagedetails.presentation.ui.compose.main.app_list.view_model.AppListViewModel
import dr.web.packagedetails.presentation.ui.navigation.AppListNavigation

@Composable
fun AppListNav() {
    val navigator = rememberNavController()
    NavHost(
        startDestination = AppListNavigation.AppList,
        navController = navigator,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable<AppListNavigation.AppList> {
            val viewModel: AppListViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.onTriggerEvent(AppListEvent.GetAppList)
            }
            AppListScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                errors = viewModel.errors,
            )
        }
    }
}