package com.carterchen247.currencyfinder.di

import com.carterchen247.currencyfinder.data.Repository
import com.carterchen247.currencyfinder.data.RepositoryImpl
import com.carterchen247.currencyfinder.data.local.LocalDataSource
import com.carterchen247.currencyfinder.data.local.LocalDataSourceImpl
import com.carterchen247.currencyfinder.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideRepository(localDataSource: LocalDataSource): Repository {
        return RepositoryImpl(localDataSource)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(appDatabase: AppDatabase): LocalDataSource {
        return LocalDataSourceImpl(appDatabase.currencyDataDao())
    }
}
