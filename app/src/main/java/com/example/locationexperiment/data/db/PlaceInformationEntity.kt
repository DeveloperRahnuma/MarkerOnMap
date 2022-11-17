package com.example.locationexperiment.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// data class that will hold information about each place
// but you can use in it for room database only
// for use this outside you need map it data
// to PlaceInformation class
@Entity
data class PlaceInformationEntity(
    val propertyName : String = "",
    val propertyCoordinate : String = "",
    @PrimaryKey val id: Int? = null
)
