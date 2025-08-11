package dr.web.packagedetails.presentation.ui.compose.custom_components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dr.web.packagedetails.domain.common.core.UIComponent
import dr.web.packagedetails.domain.common.core.ProgressBarState
import dr.web.packagedetails.domain.common.utils.getContrastTextColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun DefaultScreenUI(
    errors: Flow<UIComponent> = MutableSharedFlow(),
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    titleToolbar: String? = null,
    colorToolbar: Color? = null,
    content: @Composable (PaddingValues) -> Unit,
) {

    val messageQueue = remember { mutableStateListOf<UIComponent>() }

    Scaffold(
        topBar = {
            if (titleToolbar != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            titleToolbar,
                            style = MaterialTheme.typography.titleLarge,
                            color = getContrastTextColor(
                                colorToolbar ?: MaterialTheme.colorScheme.primary
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        },
        containerColor = colorToolbar ?: MaterialTheme.colorScheme.background,
    ) {
        Box(
            modifier =
            Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            content(it)

            LaunchedEffect(errors) {
                errors.collect { uiComponent ->
                    if (uiComponent !is UIComponent.None) {
                        messageQueue.add(uiComponent)
                    } else {
                        Log.d("DefaultScreenUI", "Received UIComponent.None with message: ${uiComponent.message}")
                    }
                }
            }

            if (messageQueue.isNotEmpty()) {
                when (val currentMessage = messageQueue.first()) {
                    is UIComponent.ToastSimple -> {
                        ShowSnackbar(
                            title = currentMessage.title,
                            snackbarVisibleState = true,
                            onDismiss = { messageQueue.removeFirstOrNull() },
                            modifier = Modifier.align(Alignment.BottomCenter),
                        )
                    }
                    is UIComponent.DialogSimple -> {
                        CreateUIComponentDialog(
                            title = currentMessage.title,
                            description = currentMessage.description,
                            onRemoveHeadFromQueue = { messageQueue.removeFirstOrNull() },
                        )
                    }

                    else -> {  }
                }
            }


            if (progressBarState is ProgressBarState.ScreenLoading) {
                CircularProgressIndicator()
            }
        }
    }
}
