package dr.web.packagedetails.presentation.ui.compose.main.app_list.view_model

import dr.web.packagedetails.domain.app_list.model.AppInfo
import dr.web.packagedetails.domain.common.core.ProgressBarState
import dr.web.packagedetails.domain.common.core.ViewState

data class AppListState(
    val apps: List<AppInfo> = emptyList(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
): ViewState