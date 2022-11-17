package com.example.locationexperiment.presentation.maps

import androidx.lifecycle.ViewModel
import com.example.locationexperiment.domain.model.PlaceInformation
import com.example.locationexperiment.domain.repository.PropertyDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val propertyDetails: PropertyDetails) : ViewModel() {

    // this function will called save data on database
    suspend fun savePlaceInDB(placeInformation: PlaceInformation){
        propertyDetails.setPlaceInformation(placeInformation)
    }
}