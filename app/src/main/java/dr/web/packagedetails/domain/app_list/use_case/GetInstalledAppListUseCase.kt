package dr.web.packagedetails.domain.app_list.use_case

import dr.web.packagedetails.domain.app_list.repository.AppListRepository
import javax.inject.Inject

class GetInstalledAppListUseCase
@Inject
constructor(private val repository: AppListRepository) {
    operator fun invoke() = repository.getInstalledAppList()
}