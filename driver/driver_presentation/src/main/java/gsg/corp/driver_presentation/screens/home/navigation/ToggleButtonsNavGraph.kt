package gsg.corp.driver_presentation.screens.home.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gsg.corp.core_ui.navigation.NavigationRouteDriver

@Composable
fun ToggleButtonsNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavigationRouteDriver.BottomNavRoutes.route,
        startDestination = NavigationRouteDriver.BottomNavToday.route,
    ) {
        composable(
            route = NavigationRouteDriver.BottomNavToday.route
        ) {

        }

        composable(
            route = NavigationRouteDriver.BottomNavWeekly.route
        ) {

        }

        composable(
            route = NavigationRouteDriver.BottomNavMonthly.route
        ) {

        }
    }
}