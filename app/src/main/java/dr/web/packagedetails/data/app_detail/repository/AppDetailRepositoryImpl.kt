package dr.web.packagedetails.data.app_detail.repository

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import dr.web.packagedetails.domain.app_detail.model.AppDetail
import dr.web.packagedetails.domain.app_detail.repository.AppDetailRepository
import dr.web.packagedetails.domain.common.core.DataState
import dr.web.packagedetails.domain.common.core.ProgressBarState
import dr.web.packagedetails.domain.common.utils.handleLocalException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.security.MessageDigest
import javax.inject.Inject

class AppDetailRepositoryImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
): AppDetailRepository{
    override fun getAppDetailInfo(packageName: String): Flow<DataState<AppDetail>> =
        flow {
            emit(DataState.Loading(progressBarState = ProgressBarState.ScreenLoading))
            try {
                val packageManager = context.packageManager

                val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0L))
                } else {
                    @Suppress("DEPRECATION")
                    packageManager.getPackageInfo(packageName, 0)
                }

                val appName = packageInfo.applicationInfo?.loadLabel(packageManager).toString()
                val appIcon = packageInfo.applicationInfo?.loadIcon(packageManager)
                val versionName = packageInfo.versionName ?: "N/A"
                val sourceDir = packageInfo.applicationInfo?.sourceDir

                val apkHash = sourceDir?.let { calculateApkHash(it) }

                if (appIcon != null) {
                    val appDetail = AppDetail(
                        name = appName,
                        icon = appIcon,
                        packageName = packageName,
                        versionName = versionName,
                        apkHash = apkHash
                    )
                    emit(DataState.Data(appDetail))
                } else {
                    emit(handleLocalException(Exception(), "Не удалось загрузить иконку приложения"))
                }

            } catch (e: PackageManager.NameNotFoundException) {
                emit(handleLocalException(e, "Приложение не найдено"))
            } catch (e: Exception) {
                emit(handleLocalException(e, "Ошибка при получении подробной информации"))
            } finally {
                emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
            }

        }

    private fun calculateApkHash(sourceDir: String): String {
        val file = File(sourceDir)
        if (!file.exists()) {
            return "Файл не найден"
        }

        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            file.inputStream().use { inputStream ->
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                var bytesRead = inputStream.read(buffer)
                while (bytesRead != -1) {
                    digest.update(buffer, 0, bytesRead)
                    bytesRead = inputStream.read(buffer)
                }
            }
            digest.digest().joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            "Ошибка вычисления хеша"
        }
    }
}