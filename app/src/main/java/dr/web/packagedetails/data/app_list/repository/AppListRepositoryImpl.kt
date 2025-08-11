package dr.web.packagedetails.data.app_list.repository

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import dr.web.packagedetails.domain.app_list.model.AppInfo
import dr.web.packagedetails.domain.app_list.repository.AppListRepository
import dr.web.packagedetails.domain.common.core.DataState
import dr.web.packagedetails.domain.common.core.ProgressBarState
import dr.web.packagedetails.domain.common.utils.handleLocalException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppListRepositoryImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
) : AppListRepository {

    override fun getInstalledAppList(): Flow<DataState<List<AppInfo>>> =
        flow {
            emit(DataState.Loading(progressBarState = ProgressBarState.ScreenLoading))
            try {
                val packageManager = context.packageManager

                val packages = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    packageManager.getInstalledPackages(PackageManager.PackageInfoFlags.of(0L))
                } else {
                    @Suppress("DEPRECATION")
                    packageManager.getInstalledPackages(0)
                }

                val appList = packages
                    .filter { (it.applicationInfo?.flags?.and(ApplicationInfo.FLAG_SYSTEM)) == 0 }
                    .mapNotNull { packageInfo ->
                        packageInfo.applicationInfo?.let { appInfo ->
                            val appName = appInfo.loadLabel(packageManager).toString()
                            val appIcon = appInfo.loadIcon(packageManager)
                            val appPackageName = appInfo.packageName
                            if (appIcon != null) {
                                AppInfo(
                                    name = appName,
                                    icon = appIcon,
                                    packageName = appPackageName
                                )
                            } else {
                                null
                            }
                        }
                    }
                    .sortedBy { it.name }

                emit(DataState.Data(appList))
            } catch (e: PackageManager.NameNotFoundException) {
                emit(handleLocalException(e, "Приложение не найдено"))
            } catch (e: Exception) {
                emit(handleLocalException(e, "Ошибка при получении установленных приложений"))
            } finally {
                emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
            }
        }
}