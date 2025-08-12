package dr.web.packagedetails.domain.app_detail.repository

import dr.web.packagedetails.domain.app_detail.model.AppDetail
import dr.web.packagedetails.domain.common.core.DataState
import kotlinx.coroutines.flow.Flow

interface AppDetailRepository {

    fun getAppDetailInfo(packageName: String): Flow<DataState<AppDetail>>
}