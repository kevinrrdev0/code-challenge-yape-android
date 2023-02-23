package gsg.corp.driver_presentation.screens.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.driver_presentation.screens.home.screens.MyRoutesMonthlyScreen
import gsg.corp.driver_presentation.screens.home.screens.MyRoutesTodayScreen
import gsg.corp.driver_presentation.screens.home.screens.MyRoutesWeekScreen

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
            MyRoutesTodayScreen()
        }

        composable(
            route = NavigationRouteDriver.BottomNavWeekly.route
        ) {
            MyRoutesWeekScreen()
        }

        composable(
            route = NavigationRouteDriver.BottomNavMonthly.route
        ) {
            MyRoutesMonthlyScreen()
        }
    }
}