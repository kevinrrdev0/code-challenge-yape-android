package gsg.corp.ruterito.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gsg.corp.core.domain.preferences.Preferences
import gsg.corp.core.preferences.DefaultPreferences
import gsg.corp.core.preferences.PreferenceManager
import gsg.corp.core.util.PREFERENCES_FILE_NAME
import gsg.corp.ruterito.BuildConfig
import gsg.corp.ruterito.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
    @Singleton
    @Provides
    fun provideAuthInterceptor(preferences: Preferences): AuthInterceptor =
        AuthInterceptor(preferences)

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ): PreferenceManager {
        return PreferenceManager(PREFERENCES_FILE_NAME,app.applicationContext)
    }

    @Provides
    @Singleton
    fun providePreferences(preferenceManager: PreferenceManager): Preferences {
        return DefaultPreferences(preferenceManager)
    }

}