package dr.web.packagedetails.presentation.ui.compose.main.app_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dr.web.packagedetails.domain.common.core.ProgressBarState
import dr.web.packagedetails.domain.common.core.UIComponent
import dr.web.packagedetails.presentation.ui.compose.custom_components.AppCard
import dr.web.packagedetails.presentation.ui.compose.custom_components.DefaultScreenUI
import dr.web.packagedetails.presentation.ui.compose.main.app_list.view_model.AppListEvent
import dr.web.packagedetails.presentation.ui.compose.main.app_list.view_model.AppListState
import kotlinx.coroutines.flow.Flow

@Composable
fun AppListScreen(
    state: AppListState,
    errors: Flow<UIComponent>,
    events: (AppListEvent) -> Unit,
) {
    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        titleToolbar = "Приложения",
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            if (state.apps.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.apps, key = {
                        it.packageName
                    }) { app ->
                        AppCard(app = app, onAppClick = {

                        })
                    }
                }
            } else if (state.progressBarState is ProgressBarState.Idle) {
                Text(
                    text = "Список приложений пуст",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}