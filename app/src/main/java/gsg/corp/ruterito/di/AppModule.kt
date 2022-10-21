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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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