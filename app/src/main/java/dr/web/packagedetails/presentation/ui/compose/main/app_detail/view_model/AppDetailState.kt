package dr.web.packagedetails.presentation.ui.compose.main.app_detail.view_model

import dr.web.packagedetails.domain.app_detail.model.AppDetail
import dr.web.packagedetails.domain.common.core.ProgressBarState
import dr.web.packagedetails.domain.common.core.ViewState

data class AppDetailState(
    val appDetail: AppDetail? = null,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
): ViewState