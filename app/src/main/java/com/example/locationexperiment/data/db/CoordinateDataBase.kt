package com.example.locationexperiment.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PlaceInformationEntity::class],
    version = 1
)
abstract class CoordinateDataBase : RoomDatabase(){
    abstract val dao : CoordinateDao
}