package dr.web.packagedetails.domain.common.utils

import dr.web.packagedetails.domain.common.core.UIComponent
import dr.web.packagedetails.domain.common.core.DataState

fun <T> handleLocalException(e: Exception, defaultDescription: String): DataState.Response<T> {
    e.printStackTrace()

    return DataState.Response(
        uiComponent = UIComponent.DialogSimple(
            title = "Ошибка",
            description = defaultDescription
        )
    )
}
