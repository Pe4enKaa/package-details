package dr.web.packagedetails.presentation.ui.compose.main.app_detail.view_model

import dr.web.packagedetails.domain.common.core.ViewEvent

sealed class AppDetailEvent: ViewEvent {
    data class GetDetailAppInfo(
        val value: String
    ): AppDetailEvent()
}