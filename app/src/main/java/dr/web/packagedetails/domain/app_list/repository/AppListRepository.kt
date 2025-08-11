package dr.web.packagedetails.domain.app_list.repository

import dr.web.packagedetails.domain.app_list.model.AppInfo
import dr.web.packagedetails.domain.common.core.DataState
import kotlinx.coroutines.flow.Flow

interface AppListRepository {

    fun getInstalledAppList(): Flow<DataState<List<AppInfo>>>
}