package com.example.locationexperiment.data.repository

import com.example.locationexperiment.data.db.CoordinateDataBase
import com.example.locationexperiment.data.mapper.toPlaceInformationEntity
import com.example.locationexperiment.domain.model.PlaceInformation
import com.example.locationexperiment.domain.repository.PropertyDetails
import javax.inject.Inject

class PropertyDetailsImpl @Inject constructor(private val coordinateDataBase: CoordinateDataBase): PropertyDetails {

    // this function will called save data on database
    // PropertyDetails interface implemented here
    override suspend fun setPlaceInformation(placeInformation: PlaceInformation) {
        coordinateDataBase.dao.insertProperty(placeInformation.toPlaceInformationEntity())
    }
}