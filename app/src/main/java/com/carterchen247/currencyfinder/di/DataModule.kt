package com.carterchen247.currencyfinder.di

import com.carterchen247.currencyfinder.data.Repository
import com.carterchen247.currencyfinder.data.RepositoryImpl
import com.carterchen247.currencyfinder.data.local.InMemoryDataSourceImpl
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
    fun provideRepository(): Repository {
        return RepositoryImpl(InMemoryDataSourceImpl())
    }
}
