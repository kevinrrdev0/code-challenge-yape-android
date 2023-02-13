package gsg.corp.driver_presentation.screens.routes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.driver_presentation.screens.routes.routes_detail.CollectionScreen
import gsg.corp.driver_presentation.screens.routes.routes_detail.ExpressScreen
import gsg.corp.driver_presentation.screens.routes.routes_detail.ZoneScreen

@Composable
fun CardButtonsRoutesNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = NavigationRouteDriver.BottomNavRoutes.route,
        startDestination = NavigationRouteDriver.CardButtonNavHarvest.route,
    ) {
        composable(
            route = NavigationRouteDriver.CardButtonNavHarvest.route
        ) {
            CollectionScreen()
        }
        composable(
            route = NavigationRouteDriver.CardButtonNavExpress.route
        ) {
           ExpressScreen()
        }
        composable(
            route = NavigationRouteDriver.CardButtonNavZones.route
        ) {
            ZoneScreen()
        }
    }
}