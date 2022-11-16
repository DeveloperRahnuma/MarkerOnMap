package com.example.locationexperiment.data.db

import androidx.room.*

@Dao
interface CoordinateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperty(placeInformationEntity: PlaceInformationEntity)
}