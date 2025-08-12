package dr.web.packagedetails.presentation.ui.compose.main.app_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dr.web.packagedetails.domain.common.core.ProgressBarState
import dr.web.packagedetails.domain.common.core.UIComponent
import dr.web.packagedetails.presentation.ui.compose.custom_components.DefaultScreenUI
import dr.web.packagedetails.presentation.ui.compose.main.app_detail.view_model.AppDetailEvent
import dr.web.packagedetails.presentation.ui.compose.main.app_detail.view_model.AppDetailState
import kotlinx.coroutines.flow.Flow

@Composable
fun AppDetailScreen(
    state: AppDetailState,
    errors: Flow<UIComponent>,
    events: (AppDetailEvent) -> Unit,
    popUp: () -> Unit
) {
    val context = LocalContext.current
    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        titleToolbar = state.appDetail?.name ?: "Приложение",
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = {popUp()}
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                ,
            contentAlignment = Alignment.TopCenter,
        ) {
            if (state.progressBarState is ProgressBarState.Idle) {
                state.appDetail?.let { app ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        AsyncImage(
                            model = app.icon,
                            contentDescription = "App Icon",
                            modifier = Modifier.size(96.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Название: ${app.name}")
                        Text(text = "Имя пакета: ${app.packageName}")
                        Text(text = "Версия: ${app.versionName}")
                        Text(text = "Контрольная сумма: ${app.apkHash ?: "Недоступно"}")

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = {
                            val launchIntent = context.packageManager.getLaunchIntentForPackage(app.packageName)
                            launchIntent?.let {
                                context.startActivity(it)
                            }
                        }) {
                            Text("Открыть приложение")
                        }
                    }
                } ?: run {
                    Text("Приложение не найдено.")
                }
            }
        }
    }
}