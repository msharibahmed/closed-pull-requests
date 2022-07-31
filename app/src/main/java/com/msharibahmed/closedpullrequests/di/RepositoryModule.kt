package com.msharibahmed.closedpullrequests.di

import com.msharibahmed.closedpullrequests.network.ClosedPrApi
import com.msharibahmed.closedpullrequests.repository.ClosedPrRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        closedPrApi: ClosedPrApi
    ): ClosedPrRepository {
        return ClosedPrRepository(closedPrApi)
    }
}