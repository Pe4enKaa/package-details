package dr.web.packagedetails.presentation.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppListNavigation {
    @Serializable
    data object AppList : AppListNavigation

    @Serializable
    data object AppDetail : AppListNavigation
}