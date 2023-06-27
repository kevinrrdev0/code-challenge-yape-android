package gsg.corp.onboarding_presentation.screens.map


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.DragState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import gsg.corp.core_ui.R
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmallRow
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.core_ui.global_components_ui.BoxLoadAnimation
import kotlinx.coroutines.launch


private const val TAG = "kevinrrdev"

@Composable
fun MapScreen(marker: LatLng,nameRecipe:String, onNavigateUp: () -> Unit) {
    var isMapLoaded by remember { mutableStateOf(false) }
    val defaultCameraPosition = CameraPosition.fromLatLngZoom(marker, 11f)
    val cameraPositionState = rememberCameraPositionState {
        position = defaultCameraPosition
    }

    Box(Modifier.fillMaxSize()) {
        GoogleMapView(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
            },
            marker = marker,
            nameRecipe=nameRecipe
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(  modifier = Modifier.clickable {
                onNavigateUp()
            }) {
                TextBody( text = "Regresar a la receta", textColor = RedGsg, boldHighlighting = true)
                GlobalExtraSpacerSmallRow()
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.ic_food),
                    contentDescription = "go detail action",
                    tint = RedGsg
                )
            }

        }
        if (!isMapLoaded) {
            AnimatedVisibility(
                modifier = Modifier
                    .matchParentSize(),
                visible = !isMapLoaded,
                enter = EnterTransition.None,
                exit = fadeOut()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .wrapContentSize()
                )
            }
        }
    }
}

@Composable
fun GoogleMapView(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit = {},
    marker: LatLng,
    nameRecipe:String
) {
    val recipeMarker = rememberMarkerState(position = marker)
    var circleCenter by remember { mutableStateOf(marker) }
    if (recipeMarker.dragState == DragState.END) {
        circleCenter = recipeMarker.position
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        onMapLoaded = onMapLoaded,
        onPOIClick = {
            Log.d(TAG, "POI clicked: ${it.name}")
        }
    ) {
        // Drawing on the map is accomplished with a child-based API
        val markerClick: (Marker) -> Boolean = {
            Log.d(TAG, "${it.title} was clicked")
            cameraPositionState.projection?.let { projection ->
                Log.d(TAG, "The current projection is: $projection")
            }
            false
        }
        MarkerInfoWindowContent(
            state = recipeMarker,
            title = nameRecipe,
            onClick = markerClick,
            draggable = true,
        ) {
            Text(it.title ?: "Title dddd", color = Color.Red)
        }

        Circle(
            center = circleCenter,
            fillColor = MaterialTheme.colors.secondary,
            strokeColor = MaterialTheme.colors.secondaryVariant,
            radius = 1000.0,
        )
        content()
    }
}