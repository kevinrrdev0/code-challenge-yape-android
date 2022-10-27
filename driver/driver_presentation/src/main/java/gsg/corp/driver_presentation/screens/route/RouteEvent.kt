package gsg.corp.driver_presentation.screens.route

sealed class RouteEvent{
    data class OnToggleRouteClick(val routeUiState: RouteUiState): RouteEvent()
}
