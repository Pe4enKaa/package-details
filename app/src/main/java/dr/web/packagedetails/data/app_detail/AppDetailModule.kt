package dr.web.packagedetails.data.app_detail

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dr.web.packagedetails.data.app_detail.repository.AppDetailRepositoryImpl
import dr.web.packagedetails.domain.app_detail.repository.AppDetailRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDetailModule {
    @Singleton
    @Provides
    fun provideAppDetailRepository(
        @ApplicationContext context: Context,
    ): AppDetailRepository = AppDetailRepositoryImpl(context)
}