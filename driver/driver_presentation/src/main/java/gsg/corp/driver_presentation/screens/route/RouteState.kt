package gsg.corp.driver_presentation.screens.route

import gsg.corp.driver_domain.model.Route

data class RouteState (
    val listRoutes:List<RouteUiState> = emptyList(),
    val loading: Boolean = false
)