package gsg.corp.driver_presentation.screens.routesv2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gsg.corp.core_ui.LocalSpacing
import gsg.corp.core_ui.global_components_dialogs.GlobalDialogError
import gsg.corp.core_ui.global_components_ui.BoxLoadAnimation
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_presentation.screens.routesv2.components.RouteCardItem


@Composable
fun RouteScreen(state: RouteState, onEvent: (RouteEvent) -> Unit, onGoDetail: (Int) -> Unit,onGoUpdateStatus: (Int) -> Unit) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (!state.loading){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    bottom = 16.dp
                )
            ) {
                items(state.listRoutes) { route ->
                    RouteCardItem(
                        route,
                        onClick = { onEvent(RouteEvent.OnToggleRouteClick(route)) },
                        onGoUpdateStatus = {
                            // ir a ver estado
                            // aqui se motrara la pantall del pedido su estado su comentario y sus fotos en caso tenga
                            onGoUpdateStatus(route.route.id)
                        },
                        onGoDetailStatus = {

                            onGoDetail(route.route.id)
                        })
                }
            }
        }


        BoxLoadAnimation(state.loading)

        //error se ve parte superiror versiones android 13
        Box(
            contentAlignment = Alignment.Center
        ) {
            when {
                state.messageError.isVisible -> {
                    GlobalDialogError(
                        state.messageError.isVisible,
                        text = state.messageError.description.asString(context),
                        onConfirm = {
                            onEvent(RouteEvent.OnHideError)
                        })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRouteScreen() {
    RouteScreen(
        RouteState(
            listOf(
                RouteUiState(
                    Route(address = "rpeuba")
                )
            )
        ), onEvent = {}, onGoDetail = {}, onGoUpdateStatus = {})
}
