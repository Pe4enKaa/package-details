package dr.web.packagedetails.presentation.ui.compose.main.app_list.view_model

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dr.web.packagedetails.domain.app_list.use_case.GetInstalledAppListUseCase
import dr.web.packagedetails.domain.common.core.BaseViewModel
import dr.web.packagedetails.domain.common.core.DataState
import dr.web.packagedetails.domain.common.core.ProgressBarState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject
constructor(
    private val getInstalledAppListUseCase: GetInstalledAppListUseCase,
) : BaseViewModel<AppListEvent, AppListState, Nothing>() {
    override fun setInitialState(): AppListState = AppListState()

    override fun onTriggerEvent(event: AppListEvent) {
        when (event) {
            is AppListEvent.GetAppList -> getInstalledAppList()
        }
    }

    private fun getInstalledAppList() {
        getInstalledAppListUseCase()
            .onEach { dataState ->
                when (dataState) {
                    is DataState.Response -> {
                        setError {
                            dataState.uiComponent
                        }
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            setState { copy(apps = it) }
                        }
                    }

                    is DataState.Loading -> {
                        setState {
                            copy(progressBarState = dataState.progressBarState)
                        }
                    }

                }
            }.launchIn(viewModelScope)
    }
}