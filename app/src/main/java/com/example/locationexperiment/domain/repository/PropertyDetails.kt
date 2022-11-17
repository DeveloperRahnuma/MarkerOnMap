package com.example.locationexperiment.domain.repository

import com.example.locationexperiment.domain.model.PlaceInformation


interface PropertyDetails {
    // this function will called save data on database
    suspend fun setPlaceInformation(placeInformation: PlaceInformation)
}