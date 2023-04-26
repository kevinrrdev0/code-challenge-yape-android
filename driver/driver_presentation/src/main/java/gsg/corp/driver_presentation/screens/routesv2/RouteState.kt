package gsg.corp.driver_presentation.screens.routesv2

import gsg.corp.core.global_models.MessageError

data class RouteState (
    val listRoutes:List<RouteUiState> = emptyList(),
    val loading: Boolean = false,
    val messageError: MessageError = MessageError()
)