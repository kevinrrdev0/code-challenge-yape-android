package gsg.corp.driver_presentation.screens.routes

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import gsg.corp.driver_presentation.screens.routes.components.ToggleButtonsRoutes
import gsg.corp.driver_presentation.screens.routes.navigation.ButtonsRoutesNavGraph

@Composable
fun RoutesScreen(
    viewModel: RoutesViewModel = hiltViewModel(),
    onCollectionScreen: () -> Unit,
    onExpressScreen: () -> Unit,
    onZonesScreen: () -> Unit
) {

    viewModel.onGetRoutesTypes()

    val navController = rememberNavController()

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(15.dp))
        ToggleButtonsRoutes(navController)
        Spacer(modifier = Modifier.size(15.dp))
        ButtonsRoutesNavGraph(
            navController = navController,
            onCollectionScreen = onCollectionScreen,
            onExpressScreen = onExpressScreen,
            onZonesScreen = onZonesScreen
        )
    }
}