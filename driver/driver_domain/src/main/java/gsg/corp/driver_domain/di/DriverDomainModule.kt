package gsg.corp.driver_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import gsg.corp.driver_domain.repository.DriverRepository
import gsg.corp.driver_domain.use_case.DriverUseCases
import gsg.corp.driver_domain.use_case.GetRoutes
import gsg.corp.driver_domain.use_case.UpdateRoute

@Module
@InstallIn(ViewModelComponent::class)
object DriverDomainModule {

    @ViewModelScoped
    @Provides
    fun provideDriverUseCases(
        repository: DriverRepository
    ):DriverUseCases{
        return DriverUseCases(
            getRoutes = GetRoutes(repository),
            updateRoute = UpdateRoute(repository)
        )
    }

}