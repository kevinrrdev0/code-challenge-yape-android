package gsg.corp.driver_presentation.screens.routes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import gsg.corp.core_ui.global_components_actions.GlobalCamera

@Composable
fun RoutesScreen(
    viewModel: RoutesViewModel = hiltViewModel()
) {
    Column {
        Text(text = "Homa undo soy Routes Screeemn")
    }
    viewModel.onGetRoutes()
}