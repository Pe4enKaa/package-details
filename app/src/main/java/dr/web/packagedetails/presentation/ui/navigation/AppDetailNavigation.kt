package dr.web.packagedetails.presentation.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDetailNavigation {
    @Serializable
    data object AppDetail : AppDetailNavigation
}