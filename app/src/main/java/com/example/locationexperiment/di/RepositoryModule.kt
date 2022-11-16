package com.example.locationexperiment.di

import com.example.locationexperiment.data.repository.PropertyDetailsImpl
import com.example.locationexperiment.domain.repository.PropertyDetails
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPropertyDetailsImpl(
        propertyDetailsImpl: PropertyDetailsImpl
    ) : PropertyDetails


}