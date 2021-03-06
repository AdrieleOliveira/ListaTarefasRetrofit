package com.example.listatarefas

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MapsFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var googleMap: GoogleMap
    private lateinit var locationCallback: LocationCallback


    private val callback = OnMapReadyCallback { googleMap ->

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment?.getMapAsync { googleMap ->
            this.googleMap = googleMap

            val paranagua = LatLng(-25.513977, -48.515772)
            addMarker(paranagua, "Paranaguá")

            googleMap.uiSettings.isZoomControlsEnabled = true

            Dexter
                .withContext(activity)
                .withPermissions(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .withListener(object  :
                MultiplePermissionsListener {
                    @SuppressLint("MissingPermission")
                    override fun onPermissionsChecked(p: MultiplePermissionsReport) {
                        if(p.areAllPermissionsGranted()){
                            googleMap.isMyLocationEnabled = true
                            getLastLocation()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?
                    ) {

                    }
                })
                .check()
        }

        mapFragment?.getMapAsync(callback)
    }

    private fun addMarker(position: LatLng, title: String? = null, moveCamera: Boolean?= true){
        googleMap.addMarker(
            MarkerOptions().position(position).title(title)
        ).isDraggable = true

        val camera = CameraUpdateFactory.newLatLngZoom(position, 15F)
        googleMap.moveCamera(camera)
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if(location != null){
                    addMarker(LatLng(location.latitude, location.longitude), "I'm here!")
                }
            }
    }

}