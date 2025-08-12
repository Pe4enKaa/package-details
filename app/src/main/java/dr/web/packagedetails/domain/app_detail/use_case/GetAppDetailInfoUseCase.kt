package dr.web.packagedetails.domain.app_detail.use_case

import dr.web.packagedetails.domain.app_detail.repository.AppDetailRepository
import javax.inject.Inject

class GetAppDetailInfoUseCase
@Inject
constructor(private val repository: AppDetailRepository) {
    operator fun invoke(packageName: String) = repository.getAppDetailInfo(packageName)
}