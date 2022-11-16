package com.example.locationexperiment.data.mapper

import com.example.locationexperiment.data.db.PlaceInformationEntity
import com.example.locationexperiment.domain.model.PlaceInformation


fun PlaceInformation.toPlaceInformationEntity() : PlaceInformationEntity {
    return PlaceInformationEntity(
        propertyName = propertyName,
        propertyCoordinate = propertyCoordinate
    )
}


