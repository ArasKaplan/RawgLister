package com.example.trendyol_intern_project_v2.dependencyinjection

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher():CoroutineDispatcher=Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher():CoroutineDispatcher=Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher():CoroutineDispatcher=Dispatchers.Main

}



@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher