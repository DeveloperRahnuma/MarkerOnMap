package com.example.locationexperiment.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlaceInformationEntity(
    val propertyName : String = "",
    val propertyCoordinate : String = "",
    @PrimaryKey val id: Int? = null
)
