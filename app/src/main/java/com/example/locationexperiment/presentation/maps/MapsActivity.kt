package com.example.locationexperiment.presentation.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.locationexperiment.R
import com.example.locationexperiment.databinding.ActivityMapsBinding
import com.example.locationexperiment.domain.model.PlaceInformation
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    // used for get runtime permission for access location
    private val permissionId = 42

    // used for make map instance globule and its initialise in onMapReady
    private lateinit var mMap: GoogleMap
    lateinit var mFusedLocationClient: FusedLocationProviderClient

    //ActivityMainBinding generated from xml file name
    //That's bind with this activity to display content here
    private lateinit var binding: ActivityMapsBinding

    // Current location is set to India, this will be of no use just for a demo data set
    var currentLocation: LatLng = LatLng(20.5, 78.9)

    // view model instance it should be provided by hilt but hilt current version
    // have some issue so
    // viewModel: MainViewModel? by viewmodel()
    // not working
    private lateinit var mapViewModel : MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ActivityMainBinding generated from xml file name
        //That's bind with this activity to display content here
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Getting an instance of viewmodel here and why we are creating an instance like this
        //Explained up the side where ViewModel declared
        mapViewModel = ViewModelProvider(this).get(MapViewModel::class.java)

        // Initializing fused location client
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.centerButtonClick.setOnClickListener {
            // if form visible means user already click and marker are placed on screen
            // so user have to clear that marker otherwise add one
            if(binding.bottomFormContainer.visibility == View.VISIBLE){
                whenFromVisible()
            }else{
                placeMarkerOnCenterOfScreen()
            }
        }

        binding.bottomFormInclude.submitButton.setOnClickListener {
            validatePropertyInfo()
        }
    }

    // Get current location and set zoom level if get location otherwise try to new one
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()){
                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                        val location: Location? = task.result
                        if (location == null) {
                            requestNewLocationData()
                        } else {
                            currentLocation = LatLng(location.latitude, location.longitude)
                            mMap.clear()
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14F))
                        }
                    }

            }
        }else{
            requestPermissions()
        }
    }


    // Get current location, if shifted  from previous location
    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
    }

    // function to check if GPS is on
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    // If current location could not be located, use last location
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            currentLocation = LatLng(mLastLocation?.latitude, mLastLocation?.longitude)
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // for loading map in satellite view remove this will load map in map view
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

        // map will load with the mark where is user mobile location ( must need to gps open )
        mMap.isMyLocationEnabled = true

        mMap.animateCamera( CameraUpdateFactory.zoomTo( 27.0f ))

        mMap.setOnMapClickListener { point ->
            openBottomSheet("${point.latitude.toString()},${point.longitude.toString()}")
            val touchedPlace = LatLng(point.latitude, point.longitude)
            mMap.addMarker(MarkerOptions().position(touchedPlace))
        }
        mMap.setOnMarkerClickListener {
            Toast.makeText(applicationContext, "Click On Marker", Toast.LENGTH_LONG).show()
            false
        }

        getLastLocation()
    }

    // Check if location permissions are
    // granted to the application
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    // Request permissions if not granted before
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), permissionId)
    }

    // What must happen when permission is granted
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }


    //for open bottom sheet when use click on floating action button
    private fun openBottomSheet(coordinate : String){
        binding.bottomFormInclude.propertyAddressValue.setText(coordinate.toString())
        binding.bottomFormInclude.propertyNameValue.setText("")
        binding.bottomFormContainer.visibility = View.VISIBLE
        binding.centerButtonClick.setImageResource(R.drawable.close_48)
    }


    // this function will place marker on center of screen
    // for that we are using mMap.cameraPosition.target
    private fun placeMarkerOnCenterOfScreen(){
        val lat  = mMap.cameraPosition.target.latitude
        val long = mMap.cameraPosition.target.longitude
        currentLocation = LatLng(lat, long)
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(currentLocation))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14F))
        openBottomSheet("${lat},${long}")
    }

    // check if user enter data in property name only because property data will added
    // automatically
    private fun validatePropertyInfo(){
        if(binding.bottomFormInclude.propertyNameValue.text.isNotEmpty() && binding.bottomFormInclude.propertyAddressValue.text.isNotEmpty()){
           // if name is entered save the detail in room database
            enterPropertyDetailIntoDB()
            Toast.makeText(this, "Data Enter successfully Into Database",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, " please enter property name to save",Toast.LENGTH_LONG).show()
        }
    }

    // enter data on defult thread and using GlobalScope you can use life scope that will run with activity
    private fun enterPropertyDetailIntoDB(){
        GlobalScope.launch(Dispatchers.Default) {
            mapViewModel.savePlaceInDB(PlaceInformation(propertyName = binding.bottomFormInclude.propertyNameValue.text.toString(), propertyCoordinate = binding.bottomFormInclude.propertyNameValue.text.toString()))
        }
    }

    //if there form already visible on screen, clear marker and coordinate vale and close
    // that form also
    private fun whenFromVisible(){
        mMap.clear()
        binding.bottomFormInclude.propertyAddressValue.setText("")
        binding.bottomFormContainer.visibility = View.GONE
        binding.centerButtonClick.setImageResource(R.drawable.plusmark)
    }

    override fun onBackPressed() {
        //if there is bottom sheet visible for user first close it otherwise
        // distroy the activity
        if(binding.bottomFormContainer.visibility == View.VISIBLE){
            binding.bottomFormContainer.visibility = View.GONE
        }else{
            super.onBackPressed()
        }
    }

}