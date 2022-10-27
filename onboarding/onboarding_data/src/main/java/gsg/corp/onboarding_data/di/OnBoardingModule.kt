package gsg.corp.onboarding_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gsg.corp.core.util.BASE_URL
import gsg.corp.core.util.ConnectionUtils
import gsg.corp.onboarding_data.remote.OnBoardingApi
import gsg.corp.onboarding_data.repository.OnBoardingRepositoryImpl
import gsg.corp.onboarding_domain.repository.OnBoardingRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnBoardingModule {

    // one api for one module
    @Provides
    @Singleton
    fun provideOnBoardingApi(client: OkHttpClient): OnBoardingApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()
        .create()

    @Provides
    @Singleton
    fun provideOnBoardingRepository(
        api: OnBoardingApi,
        connectionUtils: ConnectionUtils
    ): OnBoardingRepository = OnBoardingRepositoryImpl(api, connectionUtils)

}