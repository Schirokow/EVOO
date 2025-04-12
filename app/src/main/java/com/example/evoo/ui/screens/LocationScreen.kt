package com.example.evoo.ui.screens

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.evoo.AccentColor
import com.example.evoo.BackgroundColor
import com.example.evoo.ui.menu.MenuBar
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationCallback
//import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AccentColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues())
                .background(BackgroundColor)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                OpenStreetMapWithLiveLocation()
            }
            MenuBar(navController)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OpenStreetMapWithLiveLocation() {
    val context = LocalContext.current
    var mapView by remember { mutableStateOf<MapView?>(null) }

    // OSMDroid Konfiguration
    LaunchedEffect(Unit) {
        Configuration.getInstance().apply {
            userAgentValue = context.packageName
            load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
        }
        mapView = MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            minZoomLevel = 12.0
            maxZoomLevel = 20.0
            controller.setZoom(15.0)
        }
    }

    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    var lastLocation by remember { mutableStateOf<GeoPoint?>(null) }

    // Kartenansicht
    mapView?.let { mv ->
        AndroidView(
            factory = { mv },
            modifier = Modifier.fillMaxSize(),
            update = { view ->
                if (locationPermissionState.status.isGranted) {
                    lastLocation?.let {
                        view.controller.animateTo(it)
                        view.overlays.removeIf { true }
                        Marker(view).apply {
                            position = it
                            setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                            title = "Aktuelle Position"
                        }.also { marker ->
                            view.overlays.add(marker)
                        }
                    }
                }
            }
        )
    }

    if (locationPermissionState.status.isGranted) {
        LocationHandler { location ->
            lastLocation = GeoPoint(location.latitude, location.longitude)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Standortberechtigung benötigt")
            Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                Text("Berechtigung anfordern")
            }
        }
    }
}

@Composable
fun LocationHandler(onLocationUpdate: (Location) -> Unit) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    DisposableEffect(Unit) {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let(onLocationUpdate)
            }
        }

        val locationRequest = LocationRequest.create().apply {
            interval = 5000
            fastestInterval = 2000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }

        onDispose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}