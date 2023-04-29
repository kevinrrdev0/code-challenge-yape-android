package gsg.corp.driver_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gsg.corp.core.util.ConnectionUtils
import gsg.corp.driver_data.remote.DriverApi
import gsg.corp.driver_data.repository.DriverRepositoryImpl
import gsg.corp.driver_domain.repository.DriverRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DriverDataModule {

    @Provides
    @Singleton
    fun provideDriverApi(client: OkHttpClient,@Named("baseUrl") baseUrl: String): DriverApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDriverRepository(api: DriverApi,connectionUtils: ConnectionUtils):DriverRepository{
        return DriverRepositoryImpl(api,connectionUtils)
    }

}