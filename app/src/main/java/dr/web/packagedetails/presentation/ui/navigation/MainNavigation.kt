package dr.web.packagedetails.presentation.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface MainNavigation {
    @Serializable
    data object AppList : AppListNavigation
}