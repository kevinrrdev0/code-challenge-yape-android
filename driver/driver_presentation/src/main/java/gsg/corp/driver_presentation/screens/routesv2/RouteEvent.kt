package gsg.corp.driver_presentation.screens.routesv2

sealed class RouteEvent{
    data class OnToggleRouteClick(val routeUiState: RouteUiState): RouteEvent()
    object OnHideError : RouteEvent()
}