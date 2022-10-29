package gsg.corp.core_ui.navigation

sealed class NavigationRouteOnBoarding(val route: String) {
    object Login : NavigationRouteOnBoarding("login")
}