package com.example.minimalweather.di

import android.app.Application
import com.example.minimalweather.Persistence.AppDatabase
import com.example.minimalweather.Persistence.CurrentWeatherDao
import com.example.minimalweather.Persistence.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return TODO("Provide the return value")
    }

    @Provides
    @Singleton
    fun provideCurrentWeatherDao(appDatabase: AppDatabase): CurrentWeatherDao {
        return TODO("Provide the return value")//appDatabase.currentWeatherDao()
    }

    @Provides
    @Singleton
    fun provideLocationDao(appDatabase: AppDatabase): LocationDao {
        return TODO("Provide the return value")//appDatabase.locationDao()
    }
}