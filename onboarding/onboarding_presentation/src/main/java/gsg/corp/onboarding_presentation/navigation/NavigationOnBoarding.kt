package gsg.corp.onboarding_presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import gsg.corp.core_ui.navigation.NavigationRouteModule
import gsg.corp.onboarding_presentation.screens.login.LoginScreen

object NavigationOnBoarding {
    fun NavGraphBuilder.navigateOnBoarding(
        navController: NavHostController
    ) {
        navigation(
            route = NavigationRouteModule.ModuleOnBoarding.route,
            startDestination = NavigationRouteOnBoarding.Login.route
        ) {
            composable(
                route = NavigationRouteOnBoarding.Login.route
            ) {
                LoginScreen( onLoginSuccess = { navController.navigate(NavigationRouteModule.ModuleDriver.route) })
            }
        }

    }
}