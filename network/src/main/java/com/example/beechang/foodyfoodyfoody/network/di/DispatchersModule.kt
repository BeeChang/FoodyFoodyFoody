package com.example.beechang.foodyfoodyfoody.network.di

import com.example.beechang.foodyfoodyfoody.network.Dispatcher
import com.example.beechang.foodyfoodyfoody.network.FoodyDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Dispatcher(FoodyDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}