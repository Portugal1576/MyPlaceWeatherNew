package com.rubtsov.port.myplaceweathernew.presentation.screens.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rubtsov.port.myplaceweathernew.R
import com.rubtsov.port.myplaceweathernew.presentation.screens.MainViewModel
import com.rubtsov.port.myplaceweathernew.presentation.theme.BlueStroke
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker

@SuppressLint("ClickableViewAccessibility")
@Composable
fun MapComponent(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val lat = viewModel.lat
    val lng = viewModel.lng

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            DisplayCoordinates(lat = lat, lng = lng, color = Color.White, 14.sp)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            MapCard(
                lat = lat,
                lng = lng,
                zoomLevel = 12.0,
                onLocationSelected = { selectedLat, selectedLng ->
                    viewModel.updateLocation(selectedLat, selectedLng)
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(8.dp, bottom = 25.dp),
            contentAlignment = Alignment.Center
        ) {
            SelectCityText()
        }
    }
}

@SuppressLint("UseCompatLoadingForDrawables")
@Composable
fun MapCard(
    lat: Double,
    lng: Double,
    zoomLevel: Double,
    onLocationSelected: (Double, Double) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Blue
        ),
        border = BorderStroke(width = 1.dp, color = BlueStroke)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    Configuration.getInstance().load(
                        context,
                        androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
                    )

                    val mapView = MapView(context)
                    mapView.setTileSource(TileSourceFactory.MAPNIK)
                    mapView.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
                    mapView.setMultiTouchControls(true)

                    val mapEventsReceiver = object : MapEventsReceiver {
                        override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                            return false
                        }

                        override fun longPressHelper(p: GeoPoint?): Boolean {
                            p?.let {
                                onLocationSelected(it.latitude, it.longitude)
                            }
                            return true
                        }
                    }

                    mapView.overlays.add(MapEventsOverlay(mapEventsReceiver))

                    mapView
                },
                update = { mapView ->
                    val center = GeoPoint(lat, lng)
                    mapView.controller.setZoom(zoomLevel)
                    mapView.controller.setCenter(center)

                    // Видалення попередніх маркерів
                    val markers = mapView.overlays.filterIsInstance<Marker>()
                    mapView.overlays.removeAll(markers)

                    // Створення нового маркера з кастомною іконкою
                    val marker = Marker(mapView).apply {
                        position = center
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        title = "Ваше поточне місцезнаходження"


                        icon = mapView.context.getDrawable(R.drawable.loc_place)?.apply {
                            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
                        }
                    }
                    mapView.overlays.add(marker)
                    mapView.invalidate()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMapComponentWithBlueBox() {
    val previewLat = 41.0993
    val previewLng = -7.8035

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            DisplayCoordinates(lat = previewLat, lng = previewLng, color = Color.White, 14.sp)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Blue)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Map Preview",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            SelectCityText()
        }
    }
}