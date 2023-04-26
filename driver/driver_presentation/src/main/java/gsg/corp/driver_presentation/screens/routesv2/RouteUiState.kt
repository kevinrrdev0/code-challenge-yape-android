package gsg.corp.driver_presentation.screens.routesv2

import gsg.corp.driver_domain.model.Route

data class RouteUiState(
    val route: Route,
    val isExpanded: Boolean = false
)