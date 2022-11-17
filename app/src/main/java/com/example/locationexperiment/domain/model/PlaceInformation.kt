package com.example.locationexperiment.domain.model

import androidx.room.PrimaryKey

// data class that will hold information about each place
data class PlaceInformation(
    val propertyName : String = "",
    val propertyCoordinate : String = "",
    @PrimaryKey val id: Int? = null
)