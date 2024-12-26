package com.carterchen247.currencyfinder.di

import com.carterchen247.currencyfinder.util.dispatcher.DispatcherProvider
import com.carterchen247.currencyfinder.util.dispatcher.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl()
}