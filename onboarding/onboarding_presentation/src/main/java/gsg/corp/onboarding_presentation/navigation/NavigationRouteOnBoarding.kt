package gsg.corp.onboarding_presentation.navigation

sealed class NavigationRouteOnBoarding(val route: String) {
    object Login : NavigationRouteOnBoarding("login")
}