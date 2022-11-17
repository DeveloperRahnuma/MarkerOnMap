package com.example.locationexperiment.data.db

import androidx.room.*

// Room database dao
@Dao
interface CoordinateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperty(placeInformationEntity: PlaceInformationEntity)
}