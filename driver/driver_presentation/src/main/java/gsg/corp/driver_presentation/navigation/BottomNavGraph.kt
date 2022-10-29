package gsg.corp.driver_presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.driver_presentation.screens.home.HomeScreen
import gsg.corp.driver_presentation.screens.news.NewsScreen
import gsg.corp.driver_presentation.screens.profile.ProfileScreen
import gsg.corp.driver_presentation.screens.routes.RoutesScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavigationRouteDriver.BottomNavigation.route,
        startDestination = NavigationRouteDriver.BottomNavHome.route
    ) {

        composable(
            route = NavigationRouteDriver.BottomNavHome.route
        ) {
            HomeScreen()
        }
        composable(
            route = NavigationRouteDriver.BottomNavRoutes.route
        ) {
            RoutesScreen()
        }
        composable(
            route = NavigationRouteDriver.BottomNavNews.route
        ) {
            NewsScreen()
        }
        composable(
            route = NavigationRouteDriver.BottomNavProfile.route
        ) {
            ProfileScreen()
        }

    }
}