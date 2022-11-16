package com.example.locationexperiment.domain.repository

import com.example.locationexperiment.domain.model.PlaceInformation


interface PropertyDetails {
    suspend fun setPlaceInformation(placeInformation: PlaceInformation)
}