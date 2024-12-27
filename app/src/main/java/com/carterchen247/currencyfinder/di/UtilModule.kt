package com.carterchen247.currencyfinder.di

import android.content.Context
import com.carterchen247.currencyfinder.util.dispatcher.DispatcherProvider
import com.carterchen247.currencyfinder.util.dispatcher.DispatcherProviderImpl
import com.carterchen247.currencyfinder.util.resource.CurrencyResourceProvider
import com.carterchen247.currencyfinder.util.resource.CurrencyResourceProviderImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilModule {

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideCurrencyResourceProvider(
        @ApplicationContext context: Context,
        gson: Gson,
    ): CurrencyResourceProvider {
        return CurrencyResourceProviderImpl(context, gson)
    }
}