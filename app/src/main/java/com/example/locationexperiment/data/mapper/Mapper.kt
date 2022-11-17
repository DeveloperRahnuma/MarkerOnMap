package com.example.locationexperiment.data.mapper

import com.example.locationexperiment.data.db.PlaceInformationEntity
import com.example.locationexperiment.domain.model.PlaceInformation

// extension function of class PlaceInformation and that will map data
// of PlaceInformation to PlaceInformationEntity
fun PlaceInformation.toPlaceInformationEntity() : PlaceInformationEntity {
    return PlaceInformationEntity(
        propertyName = propertyName,
        propertyCoordinate = propertyCoordinate
    )
}


