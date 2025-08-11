package dr.web.packagedetails.presentation.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavigation {
    @Serializable
    data object Main : AppNavigation
}
