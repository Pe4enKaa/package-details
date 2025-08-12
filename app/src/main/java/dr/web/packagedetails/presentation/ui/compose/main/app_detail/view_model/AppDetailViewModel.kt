package dr.web.packagedetails.presentation.ui.compose.main.app_detail.view_model

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dr.web.packagedetails.domain.app_detail.model.AppDetail
import dr.web.packagedetails.domain.app_detail.use_case.GetAppDetailInfoUseCase
import dr.web.packagedetails.domain.common.core.BaseViewModel
import dr.web.packagedetails.domain.common.core.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AppDetailViewModel
@Inject
constructor(
    private val getAppDetailInfoUseCase: GetAppDetailInfoUseCase
): BaseViewModel<AppDetailEvent, AppDetailState, Nothing>(){
    override fun setInitialState(): AppDetailState = AppDetailState()

    override fun onTriggerEvent(event: AppDetailEvent) {
        when(event) {
            is AppDetailEvent.GetDetailAppInfo -> getDetailAppInfo(event.value)
        }
    }

    private fun getDetailAppInfo(packageName: String) {
        getAppDetailInfoUseCase(packageName)
            .onEach { dataState ->
                when (dataState) {
                    is DataState.Response -> {
                        setError {
                            dataState.uiComponent
                        }
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            setState {
                                copy(appDetail = it)
                            }
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