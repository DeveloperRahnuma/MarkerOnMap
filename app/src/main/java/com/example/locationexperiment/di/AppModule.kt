package com.example.locationexperiment.di

import android.app.Application
import androidx.room.Room
import com.example.locationexperiment.data.db.CoordinateDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{


    @Provides
    @Singleton
    fun provideDataBase(app: Application) : CoordinateDataBase {
        return Room.databaseBuilder(
            app, CoordinateDataBase::class.java, "CoordinateDatabase").fallbackToDestructiveMigration().build()
    }

}