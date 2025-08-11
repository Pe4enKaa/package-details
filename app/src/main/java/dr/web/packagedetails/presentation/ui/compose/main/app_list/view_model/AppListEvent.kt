package dr.web.packagedetails.presentation.ui.compose.main.app_list.view_model

import dr.web.packagedetails.domain.common.core.ViewEvent

sealed class AppListEvent: ViewEvent {
    data object GetAppList : AppListEvent()
}