package com.example.pruebatecnicainterrapidisimo.di

import com.example.pruebatecnicainterrapidisimo.data.repository.NetworkRepository
import com.example.pruebatecnicainterrapidisimo.data.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsNetworkRepository(repo: NetworkRepositoryImpl): NetworkRepository
}