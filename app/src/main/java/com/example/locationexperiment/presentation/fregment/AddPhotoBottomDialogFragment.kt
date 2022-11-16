package com.example.locationexperiment.presentation.fregment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import com.example.locationexperiment.R
import com.example.locationexperiment.domain.model.PlaceInformation
import com.example.locationexperiment.presentation.MapViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPhotoBottomDialogFragment(private val coordinate : String) : BottomSheetDialogFragment() {
    var mapViewModel : MapViewModel? = null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {

        // get the views and attach the listener
        val view  =  inflater.inflate(R.layout.bottomsheet_design, container, false)

        mapViewModel = ViewModelProvider(this).get(MapViewModel::class.java)

        val propertyName : EditText = view.findViewById(R.id.propertyName_value)
        val propertyValue : EditText = view.findViewById(R.id.propertyAddress_value)
        propertyValue.setText(coordinate)

        val submitButton = view.findViewById<ExtendedFloatingActionButton>(R.id.submitButton)
        submitButton.setOnClickListener {
            if(propertyValue.text.isNotEmpty() && propertyName.text.isNotEmpty()){
                GlobalScope.launch {
                    mapViewModel?.savePlaceInDB(PlaceInformation(propertyName = propertyName.text.toString(), propertyCoordinate = propertyValue.text.toString()))
                }
            }else{
                Toast.makeText(activity, " please enter property name to save",Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

    companion object {
        fun newInstance(coordinate: String): AddPhotoBottomDialogFragment {
            return AddPhotoBottomDialogFragment(coordinate)
        }
    }

}