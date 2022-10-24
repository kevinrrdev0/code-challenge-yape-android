package gsg.corp.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gsg.corp.core.util.ConnectionUtils
import gsg.corp.core.util.ConnectionUtilsImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideConnectionUtils(app: Application):ConnectionUtils{
        return ConnectionUtilsImpl(app)
    }


}