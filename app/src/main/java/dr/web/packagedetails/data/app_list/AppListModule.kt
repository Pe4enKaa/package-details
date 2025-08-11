package dr.web.packagedetails.data.app_list

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dr.web.packagedetails.data.app_list.repository.AppListRepositoryImpl
import dr.web.packagedetails.domain.app_list.repository.AppListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppListModule {
    @Singleton
    @Provides
    fun provideAppListRepository(
        @ApplicationContext context: Context,
    ): AppListRepository = AppListRepositoryImpl(context)
}